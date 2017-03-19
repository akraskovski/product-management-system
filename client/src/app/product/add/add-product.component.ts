import {Component, OnInit} from "@angular/core";
import {FormGroup, FormControl, Validators} from "@angular/forms";
import {ProductService} from "../product.service";
import {Router} from "@angular/router";
import {Product} from "../../model/product";

@Component({
    selector: 'add-product-component',
    templateUrl: './add-product.component.html'
})
export class AddProductComponent implements OnInit{
    product: FormGroup;
    loading = false;

    constructor(private productService: ProductService, private router: Router) {
    }

    ngOnInit(): void {
        this.product = new FormGroup({
            name: new FormControl('', Validators.required),
            cost: new FormControl('', Validators.required),
            type: new FormControl('', Validators.required)
        });
    }

    onSubmit() {
        this.loading = true;
        this.productService.create(new Product(this.product.value.name, this.product.value.cost, this.product.value.type))
            .subscribe(result => {
                if (result === true) {
                    alert("Success!");
                    this.router.navigate(['/']);
                } else {
                    this.loading = false;
                }
            });
    }
}