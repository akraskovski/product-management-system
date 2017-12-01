import {Component, OnInit} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {Stock} from "../../model/stock";
import {CommonService} from "../../common/common.service";
import {api} from "../../constants/api";
import {regex} from "../../constants/regex";
import {User} from "../../model/user";
// import {StockService} from "../stock.service";
import {AuthorityWorker} from "../../common/authority-worker";

@Component({
    selector: 'stock-create-component',
    templateUrl: 'stock-create.component.html'
})
export class StockCreateComponent implements OnInit {
    stockForm: FormGroup;
    managerId: string;
    allManagers: User[];
    loading;

    constructor(private commonService: CommonService,/* private stockService: StockService,*/ private router: Router) {
        this.loading = false;
        this.allManagers = [];
    }

    ngOnInit(): void {
        // this.stockService.getAllManagers().subscribe(
        //     allManagers => this.allManagers = allManagers,
        //     error => this.logError(error)
        // );

        this.stockForm = new FormGroup({
            specialize: new FormControl('', Validators.required),
            address: new FormControl(''),
            managerId: new FormControl(''),
            phone: new FormControl('', [Validators.required, Validators.pattern(regex.PHONE_NUMBER)]),
            square: new FormControl('', [Validators.pattern(regex.DOUBLE)])
        });
    }

    onSubmit(): void {
        this.loading = true;
        this.commonService.create(api.STOCK, this.createAndFillStock())
            .subscribe(
                () => this.router.navigate(['stock/stock-content']),
                error => this.logError(error));
    }

    private createAndFillStock(): Stock {
        return new Stock(
            this.stockForm.value.specialize,
            this.stockForm.value.address,
            this.stockForm.value.phone,
            this.stockForm.value.square
        );
    }

    public hasAccessToManager(): boolean {
        return AuthorityWorker.componentElementAccess("ROLE_ADMIN");
    }

    logError(error: Error): void {
        this.loading = false;
        console.error('There was an error: ' + error.message ? error.message : error.toString());
        this.router.navigate(['/']);
    }
}