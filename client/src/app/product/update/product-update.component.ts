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
        let id = this.route.snapshot.params['id'];
        this.productService.loadById(id)
            .subscribe(product => {
                this.product = product;
                console.log(this.product.id);
                console.log(this.product.name);
                this.productForm = new FormGroup({
                    name: new FormControl(this.product.name, Validators.required),
                    cost: new FormControl(this.product.cost, Validators.required),
                    type: new FormControl(this.product.type, Validators.required)
                });
            });
    }

    onSubmit() {
        this.loading = true;
        this.productService.update(new Product(this.productForm.value.name, this.productForm.value.cost, this.productForm.value.type))
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