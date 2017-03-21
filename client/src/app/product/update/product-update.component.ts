import {Component, OnInit} from "@angular/core";
import {FormGroup, FormControl, Validators} from "@angular/forms";
import {ProductService} from "../product.service";
import {Router, ActivatedRoute} from "@angular/router";
import {Product} from "../../model/product";

@Component({
    selector: 'product-update-component',
    templateUrl: 'product-update.component.html'
})
export class ProductUpdateComponent implements OnInit {
    productForm: FormGroup;
    loading = false;
    product: Product;

    constructor(private productService: ProductService, private router: Router, private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.createEmptyForm();
        this.fillForm();
    }

    private createEmptyForm(): void {
        this.productForm = new FormGroup({
            name: new FormControl('', Validators.required),
            cost: new FormControl('', [Validators.required, Validators.pattern("^[0-9]+(\.[0-9]+)?$")]),
            type: new FormControl('', Validators.required)
        });
    }

    private fillForm(): void {
        this.productService.loadById(this.route.snapshot.params['id'])
            .subscribe(product => {
                this.product = product;
                this.productForm.setValue({
                    name: this.product.name,
                    cost: this.product.cost,
                    type: this.product.type
                });
            });
    }

    onSubmit() {
        this.loading = true;
        this.product.name = this.productForm.value.name;
        this.product.cost = this.productForm.value.cost;
        this.product.type = this.productForm.value.type;
        this.productService.update(this.product)
            .subscribe(result => {
                if (result === true) {
                    this.router.navigate(['product/product-content']);
                } else {
                    this.loading = false;
                    alert("Error!");
                }
            });
    }
}