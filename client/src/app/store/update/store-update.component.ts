import {Component} from "@angular/core";
import {Store} from "../../model/store";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CommonService} from "../../common/common.service";
import {Stock} from "../../model/stock";
import {ActivatedRoute, Router} from "@angular/router";
import {api} from "../../constants/api";
import {CommonFunctions} from "../../common/common-functions";
import {regex} from "../../constants/regex";
import {ImageService} from "../../common/image.service";
@Component({
    selector: 'store-update-component',
    templateUrl: 'store-update.component.html'
})
export class StoreUpdateComponent {
    storeForm: FormGroup;
    loading: boolean;
    store: Store;
    availableStocks: Stock[];
    selectedStocks: Stock[];

    constructor(private storeService: CommonService, private router: Router, private route: ActivatedRoute, private imageService: ImageService) {
        this.loading = false;
        this.availableStocks = [];
        this.selectedStocks = [];
        this.store = new Store();
    }

    ngOnInit(): void {
        this.createEmptyForm();
        this.load();
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

    private load(): void {
        this.storeService.loadById(api.STORE, this.route.snapshot.params['id'])
            .subscribe(
                store => {
                    this.store = store;
                    this.selectedStocks = this.store.stockList;
                    this.loadStocks();
                    this.fillForm(this.store);
                },
                error => this.logError(error));
    }

    private fillForm(store: Store): void {
        this.storeForm.setValue({
            name: store.name,
            details: store.details,
            address: store.address,
            phone: store.phone,
            skype: store.skype,
            discounts: store.discounts,
            mail: store.mail
        });
    }

    private loadStocks(): void {
        this.storeService.loadAll(api.STOCK)
            .subscribe(
                stockList => this.availableStocks = CommonFunctions.cleanAvailableItems(stockList, this.selectedStocks),
                error => this.logError(error))
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
        this.fillUpdatedStore();
        this.storeService.update(api.STORE, this.store)
            .subscribe(
                () => this.router.navigate(['store/store-content']),
                error => this.logError(error));
    }

    private fillUpdatedStore(): void {
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

    private logError(error: Error): void {
        this.loading = false;
        console.error('There was an error: ' + error.message ? error.message : error.toString());
        this.router.navigate(['/']);
    }
}