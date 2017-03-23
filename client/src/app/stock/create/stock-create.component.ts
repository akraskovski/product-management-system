import {Component} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Product} from "../../model/product";
import {ProductService} from "../../product/product.service";
import {Router} from "@angular/router";
import {StockService} from "../stock.service";
import {Stock} from "../../model/stock";

@Component({
    selector: 'stock-create-component',
    templateUrl: './stock-create.component.html'
})
export class StockCreateComponent {
    stockForm: FormGroup;
    availableProducts: Product[] = [];
    selectedProducts: Product[] = [];
    loading = false;

    constructor(private stockService: StockService, private productService: ProductService, private router: Router) {
    }

    ngOnInit(): void {
        this.productService.loadAll()
            .subscribe(productList => this.availableProducts = productList);
        this.stockForm = new FormGroup({
            specialize: new FormControl('', Validators.required),
        });
    }

    onSubmit() {
        this.loading = true;
        let stock: Stock = new Stock(this.stockForm.value.specialize, this.selectedProducts);
        this.stockService.create(stock)
            .subscribe(result => {
                if (result === true) {
                    this.router.navigate(['stock/stock-content']);
                } else {
                    this.loading = false;
                    alert("Error!");
                }
            });
    }

    addProductToSelected(product: Product): void {
        this.availableProducts.splice(this.availableProducts.indexOf(product), 1);
        this.selectedProducts.push(product);
    }

    deleteProductFromSelected(product: Product): void {
        this.selectedProducts.splice(this.selectedProducts.indexOf(product), 1);
        this.availableProducts.push(product);
    }
}