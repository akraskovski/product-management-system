import {Component} from "@angular/core";
import {AuthorityWorker} from "../common/authority-worker";
import {CommonService} from "../common/common.service";

@Component({
    selector: 'store-component',
    templateUrl: 'store.component.html'
})
export class StoreComponent extends AuthorityWorker {

    constructor(private imageService: CommonService) {
        super();
    }

    fileChange(event) {
        let fileList: FileList = event.target.files;
        if (fileList.length > 0) {
            let file: File = fileList[0];
            let formData: FormData = new FormData();
            formData.append('file', file);
            this.imageService.upload(formData)
                .subscribe(
                    () => console.log("success"),
                    error => console.log(error));
        }
    }
}