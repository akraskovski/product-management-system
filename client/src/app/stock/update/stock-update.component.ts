import {Component, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Stock} from "../../model/stock";
import {Product} from "../../model/product";
import {CommonService} from "../../common/common.service";
import {ActivatedRoute, Router} from "@angular/router";
import {api} from "../../constants/api";
import {CommonFunctions} from "../../common/common-functions";
@Component({
    selector: 'stock-update-component',
    templateUrl: './stock-update.component.html'
})
export class StockUpdateComponent implements OnInit {
    stockForm: FormGroup;
    loading: boolean = false;
    stock: Stock;
    availableProducts: Product[] = [];
    selectedProducts: Product[] = [];

    constructor(private stockService: CommonService, private router: Router, private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.createEmptyForm();
        this.fillForm();
    }

    private createEmptyForm(): void {
        this.stockForm = new FormGroup({
            specialize: new FormControl('', Validators.required),
        });
    }

    private fillForm(): void {
        this.stockService.loadById(api.STOCK, this.route.snapshot.params['id'])
            .subscribe(
                stock => {
                    this.stock = stock;
                    this.selectedProducts = this.stock.productList;
                    this.loadProducts();
                    this.stockForm.setValue({
                        specialize: this.stock.specialize
                    });
                },
                err => this.logError(err));
    }

    private loadProducts(): void {
        this.stockService.loadAll(api.PRODUCT)
            .subscribe(
                productList => this.availableProducts = CommonFunctions.cleanAvailableItems(productList, this.selectedProducts),
                err => this.logError(err))
    }

    onSubmit(): void {
        this.loading = true;
        this.stock.specialize = this.stockForm.value.specialize;
        this.stock.productList = this.selectedProducts;
        this.stockService.update(api.STOCK, this.stock)
            .subscribe(
                () => this.router.navigate(['stock/stock-content']),
                err => this.logError(err));
    }

    addProductToSelected(product: Product): void {
        this.availableProducts.splice(this.availableProducts.indexOf(product), 1);
        this.selectedProducts.push(product);
    }

    deleteProductFromSelected(product: Product): void {
        this.selectedProducts.splice(this.selectedProducts.indexOf(product), 1);
        this.availableProducts.push(product);
    }

    private logError(err) {
        this.loading = false;
        console.error('There was an error: ' + err);
        this.router.navigate(['/']);
    }
}