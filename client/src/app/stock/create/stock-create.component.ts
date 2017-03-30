import {Component, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Product} from "../../model/product";
import {Router} from "@angular/router";
import {Stock} from "../../model/stock";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";

@Component({
    selector: 'stock-create-component',
    templateUrl: './stock-create.component.html'
})
export class StockCreateComponent implements OnInit {
    stockForm: FormGroup;
    availableProducts: Product[] = [];
    selectedProducts: Product[] = [];
    loading = false;

    constructor(private stockService: CommonService, private router: Router) {
    }

    ngOnInit(): void {
        this.loadProducts();
        this.createEmptyForm();
    }

    private loadProducts(): void {
        this.stockService.loadAll(api.PRODUCT)
            .subscribe(
                productList => this.availableProducts = productList,
                err => this.logError(err));
    }

    private createEmptyForm(): void {
        this.stockForm = new FormGroup({
            specialize: new FormControl('', Validators.required),
        });
    }

    onSubmit() {
        this.loading = true;
        const stock: Stock = new Stock(this.stockForm.value.specialize, this.selectedProducts);
        this.stockService.create(api.STOCK, stock)
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

    logError(err) {
        this.loading = false;
        console.error('There was an error: ' + err);
        this.router.navigate(['/']);
    }
}