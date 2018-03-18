import {Component} from "@angular/core";
import {Product} from "../../model/product";
import {Router} from "@angular/router";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";
import {NotificationService} from "../../notification/notification.service";
import {CommonFunctions} from "../../common/common-functions";

@Component({
    selector: 'product-search-component',
    templateUrl: 'product-search.component.html'
})
export class ProductSearchComponent {
    getImageUrl = CommonFunctions.getImageUrl;
    keyword: string;
    selectedParameter: string;
    availableParameters: string[];
    findProducts: Product[];
    selectedProduct: Product;

    constructor(private notificationService: NotificationService,
                private productService: CommonService,
                private router: Router) {
        this.availableParameters = ['Name', 'Type'];
        this.selectedParameter = this.availableParameters[0];
    }

    onSubmit(): void {
        if (this.keyword) {
            switch (this.selectedParameter) {
                case 'Name': {
                    this.productService.loadByName(api.PRODUCT, this.keyword)
                        .subscribe(
                            response => this.findProducts = response,
                            error => this.logError(error)
                        );
                    break;
                }
                case 'Type': {
                    this.productService.loadByType(api.PRODUCT, this.keyword)
                        .subscribe(
                            response => this.findProducts = response,
                            error => this.logError(error)
                        );
                    break;
                }
            }
        }
    }

    onDetails(product: Product) {
        if (product)
            this.selectedProduct = product;
    }

    onDelete(id: string): void {
        this.productService.remove(api.PRODUCT, id)
            .subscribe(
                () => {
                    this.notificationService.success("Product was successfully removed");
                    this.router.navigate(['product/product-content'])
                },
                error => this.logError(error));
    }

    onEdit(id: number): void {
        id ? this.router.navigate(['product/product-update', id]) : this.logError(new Error("Wrong ID!"));
    }

    logError(error): void {
        this.notificationService.error(error.message ? error.message : error.toString());
    }
}