import {Injectable} from "@angular/core";
import {Headers, Http, RequestOptions, Response} from "@angular/http";
import {api} from "../constants/api";
import {AuthorityWorker} from "../common/authority-worker";
import {Observable} from "rxjs";

@Injectable()
export class ImageService {

    constructor(private http: Http) {
    }

    upload(formData: FormData): Observable<any> {
        return this.http.post(api.IMAGE_UPLOAD, formData, this.generateOptions())
            .map((response: Response) => {
                if (response.status != 200) {
                    throw new Error('Error while uploading image! code status: ' + response.status);
                } else {
                    return response.text();
                }
            })
    }

    private generateOptions(): RequestOptions {
        const headers = new Headers({
            'x-auth-token': AuthorityWorker.getCurrentUser().token
        });
        return new RequestOptions({headers: headers});
    }
}