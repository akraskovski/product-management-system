import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {api} from "../constants/api";

@Injectable()
export class ImageService {

    constructor(private http: Http) {
    }

    upload(formData: FormData): Observable<any> {
        return this.http.post(api.IMAGE + "/upload", formData)
            .map((response: Response) => {
                if (response.status != 201) {
                    throw new Error('Error while uploading image! code status: ' + response.status);
                } else {
                    return response.text();
                }
            })
    }

    remove(id: string): Observable<any> {
        return this.http.delete(api.IMAGE + "/" + id)
            .map((response: Response) => {
                if (response.status != 200) {
                    throw new Error('Error while deleting image! code status: ' + response.status);
                }
            })
    }
}