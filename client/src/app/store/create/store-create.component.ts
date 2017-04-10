import {Component} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Stock} from "../../model/stock";
import {CommonService} from "../../common/common.service";
import {Router} from "@angular/router";
import {api} from "../../constants/api";
import {Store} from "../../model/store";
import {regex} from "../../constants/regex";

@Component({
    selector: 'store-create-component',
    templateUrl: 'store-create.component.html',
    styleUrls: ['store-create.component.css']
})
export class StoreCreateComponent {
    storeForm: FormGroup;
    availableStocks: Stock[];
    selectedStocks: Stock[];
    loading;

    constructor(private storeService: CommonService, private router: Router) {
        this.availableStocks = [];
        this.selectedStocks = [];
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
            details: new FormControl(''),
            address: new FormControl(''),
            phone: new FormControl('', [Validators.pattern(regex.PHONE_NUMBER)]),
            skype: new FormControl(''),
            discounts: new FormControl(''),
            mail: new FormControl(''),
        });
    }

    onSubmit() {
        this.loading = true;
        this.storeService.create(api.STORE, this.fillCreatingStore())
            .subscribe(
                () => this.router.navigate(['store/store-content']),
                err => this.logError(err)
            );
    }

    private fillCreatingStore(): Store {
        let store: Store = new Store();
        store.name = this.storeForm.value.name;
        store.details = this.storeForm.value.details;
        store.address = this.storeForm.value.address;
        store.phone = this.storeForm.value.phone;
        store.skype = this.storeForm.value.skype;
        store.discounts = this.storeForm.value.discounts;
        store.mail = this.storeForm.value.mail;
        store.stockList = this.selectedStocks;
        return store;
    }

    addStockToSelected(stock: Stock): void {
        this.availableStocks.splice(this.availableStocks.indexOf(stock), 1);
        this.selectedStocks.push(stock);
    }

    deleteStockFromSelected(stock: Stock): void {
        this.selectedStocks.splice(this.selectedStocks.indexOf(stock), 1);
        this.availableStocks.push(stock);
    }

    logError(error: Error) {
        this.loading = false;
        console.error('There was an error: ' + error.message ? error.message : error.toString());
        this.router.navigate(['/']);
    }
}