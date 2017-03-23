import {Component, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {Product} from "../../model/product";
import {CommonService} from "../../common/common.service";
import {environment} from "../../constants/environment";

@Component({
    selector: 'product-create-component',
    templateUrl: 'product-create.component.html'
})
export class ProductCreateComponent implements OnInit {
    productForm: FormGroup;
    loading = false;

    constructor(private productService: CommonService, private router: Router) {
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
        const product: Product = new Product(this.productForm.value.name, this.productForm.value.cost, this.productForm.value.type);
        this.productService.create(environment.PRODUCT_URL, product)
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