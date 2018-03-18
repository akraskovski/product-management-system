import {Component, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {Product} from "../../model/product";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";
import {regex} from "../../constants/regex";
import {ImageService} from "../../common/image.service";
import {NotificationService} from "../../notification/notification.service";
import {CommonFunctions} from "../../common/common-functions";

@Component({
    selector: 'product-create-component',
    templateUrl: 'product-create.component.html'
})
export class ProductCreateComponent implements OnInit {
    getImageUrl = CommonFunctions.getImageUrl;
    productForm: FormGroup;
    product: Product;
    loading: boolean;

    constructor(private notificationService: NotificationService,
                private productService: CommonService,
                private imageService: ImageService,
                private router: Router) {
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
            width: new FormControl('', Validators.pattern(regex.DOUBLE)),
            height: new FormControl('', Validators.pattern(regex.DOUBLE)),
            weight: new FormControl('', Validators.pattern(regex.DOUBLE)),
            type: new FormControl(''),
            details: new FormControl('', [Validators.pattern(regex.DETAILS)])
        });
    }

    private fillProduct(): void {
        this.product.name = this.productForm.value.name;
        this.product.cost = this.productForm.value.cost;
        this.product.width = this.productForm.value.width;
        this.product.height = this.productForm.value.height;
        this.product.weight = this.productForm.value.weight;
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

    onSubmit(): void {
        this.loading = true;
        this.fillProduct();
        this.productService.create(api.PRODUCT, this.product)
            .subscribe(
                () => {
                    this.notificationService.success("Product was successfully created");
                    this.router.navigate(['product/product-content'])
                },
                error => this.logError(error));
    }

    logError(error: Error) {
        this.loading = false;
        this.notificationService.error(error.message ? error.message : error.toString());
    }
}