import {Component, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Stock} from "../../model/stock";
import {Product} from "../../model/product";
import {CommonService} from "../../common/common.service";
import {ActivatedRoute, Router} from "@angular/router";
import {api} from "../../constants/api";
import {CommonFunctions} from "../../common/common-functions";
import {regex} from "../../constants/regex";
@Component({
    selector: 'stock-update-component',
    templateUrl: './stock-update.component.html'
})
export class StockUpdateComponent implements OnInit {
    stockForm: FormGroup;
    loading: boolean;
    stock: Stock;
    availableProducts: Product[];
    selectedProducts: Product[];

    constructor(private stockService: CommonService, private router: Router, private route: ActivatedRoute) {
        this.loading = false;
        this.availableProducts = [];
        this.selectedProducts = [];
    }

    ngOnInit(): void {
        this.createEmptyForm();
        this.load();
    }

    private createEmptyForm(): void {
        this.stockForm = new FormGroup({
            specialize: new FormControl('', Validators.required),
            address: new FormControl(''),
            phone: new FormControl('', [Validators.pattern(regex.PHONE_NUMBER)]),
            square: new FormControl('', [Validators.pattern(regex.DOUBLE)])
        });
    }

    private load(): void {
        this.stockService.loadById(api.STOCK, this.route.snapshot.params['id'])
            .subscribe(
                stock => {
                    this.stock = stock;
                    this.selectedProducts = this.stock.productList;
                    this.loadProducts();
                    this.fillForm(this.stock);
                },
                error => this.logError(error));
    }

    private fillForm(stock: Stock): void {
        this.stockForm.setValue({
            specialize: stock.specialize,
            address: stock.address,
            phone: stock.phone,
            square: stock.square
        });
    }

    private loadProducts(): void {
        this.stockService.loadAll(api.PRODUCT)
            .subscribe(
                productList => this.availableProducts = CommonFunctions.cleanAvailableItems(productList, this.selectedProducts),
                error => this.logError(error))
    }

    onSubmit(): void {
        this.loading = true;
        this.stock.specialize = this.stockForm.value.specialize;
        this.stock.productList = this.selectedProducts;
        this.stockService.update(api.STOCK, this.stock)
            .subscribe(
                () => this.router.navigate(['stock/stock-content']),
                error => this.logError(error));
    }

    addProductToSelected(product: Product): void {
        this.availableProducts.splice(this.availableProducts.indexOf(product), 1);
        this.selectedProducts.push(product);
    }

    deleteProductFromSelected(product: Product): void {
        this.selectedProducts.splice(this.selectedProducts.indexOf(product), 1);
        this.availableProducts.push(product);
    }

    private logError(error: Error) {
        this.loading = false;
        console.error('There was an error: ' + error.message ? error.message : error.toString());
        this.router.navigate(['/']);
    }
}