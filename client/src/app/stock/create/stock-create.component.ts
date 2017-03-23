import {Component} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Product} from "../../model/product";
import {Router} from "@angular/router";
import {Stock} from "../../model/stock";
import {CommonService} from "../../common/common.service";
import {environment} from "../../constants/environment";

@Component({
    selector: 'stock-create-component',
    templateUrl: './stock-create.component.html'
})
export class StockCreateComponent {
    stockForm: FormGroup;
    availableProducts: Product[] = [];
    selectedProducts: Product[] = [];
    loading = false;

    constructor(private stockService: CommonService, private router: Router) {
    }

    ngOnInit(): void {
        this.stockService.loadAll(environment.PRODUCT_URL)
            .subscribe(productList => this.availableProducts = productList);
        this.stockForm = new FormGroup({
            specialize: new FormControl('', Validators.required),
        });
    }

    onSubmit() {
        this.loading = true;
        let stock: Stock = new Stock(this.stockForm.value.specialize, this.selectedProducts);
        this.stockService.create(environment.STOCK_URL, stock)
            .subscribe(result => result ? this.router.navigate(['stock/stock-content']) : this.errorMsg);
    }

    addProductToSelected(product: Product): void {
        this.availableProducts.splice(this.availableProducts.indexOf(product), 1);
        this.selectedProducts.push(product);
    }

    deleteProductFromSelected(product: Product): void {
        this.selectedProducts.splice(this.selectedProducts.indexOf(product), 1);
        this.availableProducts.push(product);
    }

    private errorMsg(): void {
        this.loading = false;
        alert("Error while creating new stock!");
    }
}