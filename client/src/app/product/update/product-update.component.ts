import {Component, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {Product} from "../../model/product";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";
import {regex} from "../../constants/regex";

@Component({
    selector: 'product-update-component',
    templateUrl: 'product-update.component.html'
})
export class ProductUpdateComponent implements OnInit {
    productForm: FormGroup;
    loading: boolean = false;
    product: Product;

    constructor(private productService: CommonService, private router: Router, private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.createEmptyForm();
        this.fillForm();
    }

    private createEmptyForm(): void {
        this.productForm = new FormGroup({
            name: new FormControl('', Validators.required),
            cost: new FormControl('', [Validators.required, Validators.pattern(regex.DOUBLE)]),
            type: new FormControl('', Validators.required)
        });
    }

    private fillForm(): void {
        this.productService.loadById(api.PRODUCT, this.route.snapshot.params['id'])
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
        this.productService.update(api.PRODUCT, this.product)
            .subscribe(result => result ? this.router.navigate(['product/product-content']) : this.errorMsg);
    }

    private errorMsg(): void {
        this.loading = false;
        alert("Error while updating product!");
    }
}