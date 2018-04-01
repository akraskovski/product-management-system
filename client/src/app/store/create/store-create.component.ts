import {Component} from "@angular/core";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CommonService} from "../../common/common.service";
import {Router} from "@angular/router";
import {api} from "../../constants/api";
import {Store} from "../../model/store";
import {regex} from "../../constants/regex";
import {ImageService} from "../../common/image.service";
import {CommonFunctions} from "../../common/common-functions";
import {NotificationService} from "../../notification/notification.service";

@Component({
    selector: 'store-create-component',
    templateUrl: 'store-create.component.html'
})
export class StoreCreateComponent {
    getImageUrl = CommonFunctions.getImageUrl;
    storeForm: FormGroup;
    store: Store;
    loading;

    constructor(private storeService: CommonService,
                private router: Router,
                private imageService: ImageService,
                private notificationService: NotificationService) {
        this.store = new Store();
        this.loading = false;
    }

    ngOnInit(): void {
        this.createEmptyForm();
    }

    private createEmptyForm(): void {
        this.storeForm = new FormGroup({
            name: new FormControl('', Validators.required),
            details: new FormControl('', Validators.pattern(regex.DETAILS)),
            address: new FormControl(''),
            mail: new FormControl('', [Validators.required, Validators.pattern(regex.EMAIL)]),
            phone: new FormControl('', [Validators.required, Validators.pattern(regex.PHONE_NUMBER)]),
            skype: new FormControl(''),
            discounts: new FormControl(''),
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
    }

    logError(error: Error): void {
        this.loading = false;
        this.notificationService.error('There was an error: ' + error.message ? error.message : error.toString());
    }
}