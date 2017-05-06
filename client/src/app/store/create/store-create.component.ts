import {Component} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Stock} from "../../model/stock";
import {CommonService} from "../../common/common.service";
import {Router} from "@angular/router";
import {api} from "../../constants/api";
import {Store} from "../../model/store";
import {regex} from "../../constants/regex";
import {ImageService} from "../../common/image.service";

@Component({
    selector: 'store-create-component',
    templateUrl: 'store-create.component.html'
})
export class StoreCreateComponent {
    storeForm: FormGroup;
    availableStocks: Stock[];
    selectedStocks: Stock[];
    store: Store;
    loading;

    constructor(private storeService: CommonService, private router: Router, private imageService: ImageService) {
        this.availableStocks = [];
        this.selectedStocks = [];
        this.store = new Store();
        this.loading = false;
    }

    ngOnInit(): void {
        this.loadStocks();
        this.createEmptyForm();
    }

    private loadStocks(): void {
        this.storeService.loadAll(api.STOCK)
            .subscribe(
                stockList => this.availableStocks = stockList,
                error => this.logError(error));
    }

    private createEmptyForm(): void {
        this.storeForm = new FormGroup({
            name: new FormControl('', Validators.required),
            details: new FormControl('', Validators.pattern(regex.DETAILS)),
            address: new FormControl(''),
            phone: new FormControl('', [Validators.pattern(regex.PHONE_NUMBER)]),
            skype: new FormControl(''),
            discounts: new FormControl(''),
            mail: new FormControl(''),
        });
    }

    fileChange(event) {
        let fileList: FileList = event.target.files;
        if (fileList.length > 0) {
            if (this.store.logo)
                this.imageService.remove(this.store.logo)
                    .subscribe(
                        () => {
                            console.log("image with id: \"" + this.store.logo + "\" was deleted");
                            this.store.logo = null;
                        },
                        error => this.logError(error)
                    );
            let file: File = fileList[0];
            let formData: FormData = new FormData();
            formData.append('file', file);
            this.imageService.upload(formData)
                .subscribe(
                    (id) => this.store.logo = id,
                    error => this.logError(error));
        }
    }

    getImageUrl(id: string): string {
        return api.SERVER + 'image/' + id;
    }

    onSubmit(): void {
        this.loading = true;
        this.fillCreatingStore();
        this.storeService.create(api.STORE, this.store)
            .subscribe(
                () => this.router.navigate(['store/store-content']),
                err => this.logError(err)
            );
    }

    private fillCreatingStore(): void {
        this.store.name = this.storeForm.value.name;
        this.store.details = this.storeForm.value.details;
        this.store.address = this.storeForm.value.address;
        this.store.phone = this.storeForm.value.phone;
        this.store.skype = this.storeForm.value.skype;
        this.store.discounts = this.storeForm.value.discounts;
        this.store.mail = this.storeForm.value.mail;
        this.store.stockList = this.selectedStocks;
    }

    addStockToSelected(stock: Stock): void {
        this.availableStocks.splice(this.availableStocks.indexOf(stock), 1);
        this.selectedStocks.push(stock);
    }

    deleteStockFromSelected(stock: Stock): void {
        this.selectedStocks.splice(this.selectedStocks.indexOf(stock), 1);
        this.availableStocks.push(stock);
    }

    logError(error: Error): void {
        this.loading = false;
        console.error('There was an error: ' + error.message ? error.message : error.toString());
        this.router.navigate(['/']);
    }
}