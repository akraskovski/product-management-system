import {Component, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {Product} from "../../model/product";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";
import {regex} from "../../constants/regex";

@Component({
    selector: 'product-create-component',
    templateUrl: 'product-create.component.html'
})
export class ProductCreateComponent implements OnInit {
    productForm: FormGroup;
    loading: boolean = false;

    constructor(private productService: CommonService, private router: Router) {
    }

    ngOnInit(): void {
        this.productForm = new FormGroup({
            name: new FormControl('', Validators.required),
            cost: new FormControl('', [Validators.required, Validators.pattern(regex.DOUBLE)]),
            type: new FormControl('', Validators.required)
        });
    }

    onSubmit() {
        this.loading = true;
        const product: Product = new Product(this.productForm.value.name, this.productForm.value.cost, this.productForm.value.type);
        this.productService.create(api.PRODUCT, product)
            .subscribe(result => result ? this.router.navigate(['product/product-content']) : this.errorMsg);
    }

    private errorMsg(): void {
        this.loading = false;
        alert("Error while creating new product!");
    }
}