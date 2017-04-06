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
    loading: boolean;

    constructor(private productService: CommonService, private router: Router) {
        this.loading = false;
    }

    ngOnInit(): void {
        this.createEmptyForm();
    }

    private createEmptyForm(): void {
        this.productForm = new FormGroup({
            name: new FormControl('', Validators.required),
            cost: new FormControl('', [Validators.required, Validators.pattern(regex.DOUBLE)]),
            type: new FormControl(''),
            details: new FormControl('')
        });
    }

    onSubmit() {
        this.loading = true;
        const product: Product = new Product(
            this.productForm.value.name,
            this.productForm.value.cost,
            this.productForm.value.type,
            this.productForm.value.details
        );
        this.productService.create(api.PRODUCT, product)
            .subscribe(
                () => this.router.navigate(['product/product-content']),
                error => this.logError(error));
    }

    logError(error: Error) {
        this.loading = false;
        console.error('There was an error: ' + error.message ? error.message : error.toString());
        this.router.navigate(['/']);
    }
}