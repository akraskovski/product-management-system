import {Injectable} from "@angular/core";
import {Http, Headers, RequestOptions} from "@angular/http";
import {environment} from "../constants/environment";

@Injectable()
export class SecuredService {

    constructor(private http: Http) {
    }

    getMessage() {
        const userToken = JSON.parse(localStorage.getItem(environment.USER_KEY)).token;
        const headers = new Headers({'x-auth-token': userToken});
        const options = new RequestOptions({headers: headers});
        return this.http.get(environment.SECURED_URL, options)
            .map(response => response.text())
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}