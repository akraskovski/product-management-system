import {Injectable} from "@angular/core";
import {Http, Headers, RequestOptions} from "@angular/http";
import {Observable} from "rxjs";
import {environment} from "../constants/environment";
import {Stock} from "../model/stock";
import {AuthorizationService} from "../authorization/authorization.service";

@Injectable()
export class StockService {

    constructor(private http: Http) { }

    loadAll(): Observable<Stock[]> {
        return this.http.get(environment.STOCK_URL, this.generateOptions())
            .map(responce => responce.json())
            .catch(StockService.handleError);
    }

    private generateOptions(): RequestOptions {
        const headers = new Headers({
            'Content-Type': 'application/json',
            'x-auth-token': AuthorizationService.getCurrentUser().token
        });
        return new RequestOptions({headers: headers});
    }

    private static handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}