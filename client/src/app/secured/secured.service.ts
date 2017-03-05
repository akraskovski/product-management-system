import {Injectable} from "@angular/core";
import {Http, Headers, RequestOptions} from "@angular/http";
import {environment} from "../constants/environment";

@Injectable()
export class SecuredService {

    constructor(private http: Http) {
    }

    getMessage() {
        let userToken = JSON.parse(localStorage.getItem("currentUser")).token;
        let headers = new Headers({'x-auth-token': userToken});
        let options = new RequestOptions({headers: headers});
        return this.http.get(environment.SECURED_URL, options)
            .map(response => response.text())
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}