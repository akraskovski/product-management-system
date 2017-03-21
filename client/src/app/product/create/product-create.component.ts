import {Component, OnInit} from "@angular/core";
import {FormGroup, FormControl, Validators} from "@angular/forms";
import {ProductService} from "../product.service";
import {Router} from "@angular/router";
import {Product} from "../../model/product";

@Component({
    selector: 'product-create-component',
    templateUrl: 'product-create.component.html'
})
export class ProductCreateComponent implements OnInit {
    productForm: FormGroup;
    loading = false;

    constructor(private productService: ProductService, private router: Router) {
    }

    ngOnInit(): void {
        this.productForm = new FormGroup({
            name: new FormControl('', Validators.required),
            cost: new FormControl('', [Validators.required, Validators.pattern("^[0-9]+(\.[0-9]+)?$")]),
            type: new FormControl('', Validators.required)
        });
    }

    onSubmit() {
        this.loading = true;
        this.productService.create(new Product(this.productForm.value.name, this.productForm.value.cost, this.productForm.value.type))
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