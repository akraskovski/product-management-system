import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class ImageService {

    constructor(private http: Http) {
    }

    upload(formData: FormData): Observable<any> {
        return this.http.post("/image/upload", formData)
            .map((response: Response) => {
                if (response.status != 200) {
                    throw new Error('Error while uploading image! code status: ' + response.status);
                } else {
                    return response.status;
                }
            })
    }
}