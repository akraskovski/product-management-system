import {Component} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Stock} from "../../model/stock";
import {CommonService} from "../../common/common.service";
import {Router} from "@angular/router";
import {api} from "../../constants/api";
import {Store} from "../../model/store";

@Component({
    selector: 'store-create-component',
    templateUrl: './store-create.component.html'
})
export class StoreCreateComponent {
    storeForm: FormGroup;
    availableStocks: Stock[] = [];
    selectedStocks: Stock[] = [];
    loading = false;

    constructor(private storeService: CommonService, private router: Router) {
    }

    ngOnInit(): void {
        this.loadStocks();
        this.createEmptyForm();
    }

    private loadStocks(): void {
        this.storeService.loadAll(api.STOCK)
            .subscribe(
                stockList => this.availableStocks = stockList,
                err => this.logError(err));
    }

    private createEmptyForm(): void {
        this.storeForm = new FormGroup({
            name: new FormControl('', Validators.required),
        });
    }

    onSubmit() {
        this.loading = true;
        const store: Store = new Store(this.storeForm.value.name, this.selectedStocks);
        this.storeService.create(api.STORE, store)
            .subscribe(
                () => this.router.navigate(['store/store-content']),
                err => this.logError(err)
            );
    }

    addStockToSelected(stock: Stock): void {
        this.availableStocks.splice(this.availableStocks.indexOf(stock), 1);
        this.selectedStocks.push(stock);
    }

    deleteStockFromSelected(stock: Stock): void {
        this.selectedStocks.splice(this.selectedStocks.indexOf(stock), 1);
        this.availableStocks.push(stock);
    }

    logError(err) {
        this.loading = false;
        console.error('There was an error: ' + err);
        this.router.navigate(['/']);
    }
}