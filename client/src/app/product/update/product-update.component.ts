import {Component, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {Product} from "../../model/product";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";
import {regex} from "../../constants/regex";
import {ImageService} from "../../common/image.service";

@Component({
    selector: 'product-update-component',
    templateUrl: 'product-update.component.html',
    styleUrls: ['product-update.component.css']
})
export class ProductUpdateComponent implements OnInit {
    productForm: FormGroup;
    loading: boolean;
    product: Product;

    constructor(private productService: CommonService,
                private imageService: ImageService,
                private router: Router,
                private route: ActivatedRoute) {
        this.loading = false;
        this.product = new Product();
    }

    ngOnInit(): void {
        this.createEmptyForm();
        this.load();
    }

    private createEmptyForm(): void {
        this.productForm = new FormGroup({
            name: new FormControl('', Validators.required),
            cost: new FormControl('', [Validators.required, Validators.pattern(regex.DOUBLE)]),
            width: new FormControl('', Validators.pattern(regex.DOUBLE)),
            height: new FormControl('', Validators.pattern(regex.DOUBLE)),
            weight: new FormControl('', Validators.pattern(regex.DOUBLE)),
            type: new FormControl(''),
            details: new FormControl('', [Validators.pattern(regex.DETAILS)])
        });
    }

    private load(): void {
        this.productService.loadById(api.PRODUCT, this.route.snapshot.params['id'])
            .subscribe(
                product => {
                    this.product = product;
                    this.fillForm(this.product)
                },
                error => this.logError(error));
    }

    private fillForm(product: Product): void {
        this.productForm.setValue({
            name: product.name,
            cost: product.cost,
            width: product.width,
            height: product.height,
            weight: product.weight,
            type: product.type,
            details: product.details
        });
    }

    fileChange(event) {
        let fileList: FileList = event.target.files;
        if (fileList.length > 0) {
            if (this.product.image)
                this.imageService.remove(this.product.image)
                    .subscribe(
                        () => {
                            console.log("image with id: \"" + this.product.image + "\" was deleted");
                            this.product.image = null;
                        },
                        error => this.logError(error)
                    );
            let file: File = fileList[0];
            let formData: FormData = new FormData();
            formData.append('file', file);
            this.imageService.upload(formData)
                .subscribe(
                    (id) => this.product.image = id,
                    error => this.logError(error));
        }
    }

    private fillUpdatedProduct(): void {
        this.product.name = this.productForm.value.name;
        this.product.cost = this.productForm.value.cost;
        this.product.width = this.productForm.value.width;
        this.product.height = this.productForm.value.height;
        this.product.weight = this.productForm.value.weight;
        this.product.type = this.productForm.value.type;
        this.product.details = this.productForm.value.details;
    }

    onSubmit(): void {
        this.loading = true;
        this.fillUpdatedProduct();
        this.productService.update(api.PRODUCT, this.product)
            .subscribe(
                () => this.router.navigate(['product/product-content']),
                err => this.logError(err));
    }

    getImageUrl(id: string): string {
        return api.SERVER + 'image/' + id;
    }

    logError(error): void {
        this.loading = false;
        console.error('There was an error: ' + error.message ? error.message : error.toString());
        this.router.navigate(['/']);
    }
}