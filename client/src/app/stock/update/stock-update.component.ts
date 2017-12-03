import {Component, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Stock} from "../../model/stock";
import {CommonService} from "../../common/common.service";
import {ActivatedRoute, Router} from "@angular/router";
import {api} from "../../constants/api";
import {regex} from "../../constants/regex";

@Component({
    selector: 'stock-update-component',
    templateUrl: 'stock-update.component.html'
})
export class StockUpdateComponent implements OnInit {
    stockForm: FormGroup;
    loading: boolean;
    stock: Stock;

    constructor(private commonService: CommonService, private router: Router, private route: ActivatedRoute) {
        this.loading = false;
    }

    ngOnInit(): void {
        this.createEmptyForm();
        this.load();
    }

    private createEmptyForm(): void {
        this.stockForm = new FormGroup({
            specialize: new FormControl('', Validators.required),
            address: new FormControl(''),
            phone: new FormControl('', [Validators.pattern(regex.PHONE_NUMBER)]),
            square: new FormControl('', [Validators.pattern(regex.DOUBLE)])
        });
    }

    private load(): void {
        this.commonService.loadById(api.STOCK, this.route.snapshot.params['id'])
            .subscribe(
                stock => {
                    this.stock = stock;
                    this.fillForm(this.stock);
                },
                error => this.logError(error));
    }

    private fillForm(stock: Stock): void {
        this.stockForm.setValue({
            specialize: stock.specialize,
            address: stock.address,
            phone: stock.phone,
            square: stock.square
        });
    }

    onSubmit(): void {
        this.loading = true;
        this.fillUpdatedStock();
        this.commonService.update(api.STOCK, this.stock).subscribe(
            () => this.router.navigate(['stock/stock-content']),
            error => this.logError(error));
    }

    private fillUpdatedStock(): void {
        this.stock.specialize = this.stockForm.value.specialize;
        this.stock.address = this.stockForm.value.address;
        this.stock.phone = this.stockForm.value.phone;
        this.stock.square = this.stockForm.value.square;
    }

    private logError(error: Error): void {
        this.loading = false;
        console.error('There was an error: ' + error.message ? error.message : error.toString());
        this.router.navigate(['/']);
    }
}