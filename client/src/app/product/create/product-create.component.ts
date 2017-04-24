import {Component, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {Product} from "../../model/product";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";
import {regex} from "../../constants/regex";
import {ImageService} from "../../common/image.service";

@Component({
    selector: 'product-create-component',
    templateUrl: 'product-create.component.html',
    styleUrls: ['product-create.component.css']
})
export class ProductCreateComponent implements OnInit {
    productForm: FormGroup;
    product: Product;
    loading: boolean;

    constructor(private productService: CommonService, private imageService: ImageService, private router: Router) {
        this.loading = false;
        this.product = new Product();
    }

    ngOnInit(): void {
        this.createEmptyForm();
    }

    private createEmptyForm(): void {
        this.productForm = new FormGroup({
            name: new FormControl('', Validators.required),
            cost: new FormControl('', [Validators.required, Validators.pattern(regex.DOUBLE)]),
            type: new FormControl(''),
            details: new FormControl('', [Validators.pattern(regex.DETAILS)])
        });
    }

    private fillProduct(): void {
        this.product.name = this.productForm.value.name;
        this.product.cost = this.productForm.value.cost;
        this.product.type = this.productForm.value.type;
        this.product.details = this.productForm.value.details;
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

    getImageUrl(id: string): string {
        return api.SERVER + 'image/' + id;
    }

    onSubmit(): void {
        this.loading = true;
        this.fillProduct();
        this.productService.create(api.PRODUCT, this.product)
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