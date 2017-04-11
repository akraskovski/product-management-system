import {Component, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Product} from "../../model/product";
import {Router} from "@angular/router";
import {Stock} from "../../model/stock";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";
import {regex} from "../../constants/regex";

@Component({
    selector: 'stock-create-component',
    templateUrl: 'stock-create.component.html',
    styleUrls: ['stock-create.component.css']
})
export class StockCreateComponent implements OnInit {
    stockForm: FormGroup;
    availableProducts: Product[];
    selectedProducts: Product[];
    loading;

    constructor(private stockService: CommonService, private router: Router) {
        this.availableProducts = [];
        this.selectedProducts = [];
        this.loading = false;
    }

    ngOnInit(): void {
        this.loadProducts();
        this.createEmptyForm();
    }

    private loadProducts(): void {
        this.stockService.loadAll(api.PRODUCT)
            .subscribe(
                productList => this.availableProducts = productList,
                error => this.logError(error));
    }

    private createEmptyForm(): void {
        this.stockForm = new FormGroup({
            specialize: new FormControl('', Validators.required),
            address: new FormControl(''),
            phone: new FormControl('', [Validators.pattern(regex.PHONE_NUMBER)]),
            square: new FormControl('', [Validators.pattern(regex.DOUBLE)])
        });
    }

    onSubmit(): void {
        this.loading = true;
        this.stockService.create(api.STOCK, this.createAndFillStock())
            .subscribe(
                () => this.router.navigate(['stock/stock-content']),
                error => this.logError(error));
    }

    private createAndFillStock(): Stock {
        return new Stock(
            this.stockForm.value.specialize,
            this.stockForm.value.address,
            this.stockForm.value.phone,
            this.stockForm.value.square,
            this.selectedProducts
        );
    }

    addProductToSelected(product: Product): void {
        this.availableProducts.splice(this.availableProducts.indexOf(product), 1);
        this.selectedProducts.push(product);
    }

    deleteProductFromSelected(product: Product): void {
        this.selectedProducts.splice(this.selectedProducts.indexOf(product), 1);
        this.availableProducts.push(product);
    }

    logError(error: Error): void {
        this.loading = false;
        console.error('There was an error: ' + error.message ? error.message : error.toString());
        this.router.navigate(['/']);
    }
}