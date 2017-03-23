import {Injectable} from "@angular/core";
import {Http, Response, Headers, RequestOptions} from "@angular/http";
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

    loadById(identifier: number) {
        return this.http.get(environment.STOCK_URL + "/" + identifier, this.generateOptions())
            .map((response: Response) => response.json())
            .catch(StockService.handleError);
    }

    create(stock: Stock) {
        return this.http.post(environment.STOCK_URL, stock, this.generateOptions())
            .map((response: Response) => response.status === 201)
            .catch(StockService.handleError);
    }

    update(stock: Stock) {
        return this.http.put(environment.STOCK_URL, stock, this.generateOptions())
            .map((response: Response) => response.status === 200)
            .catch(StockService.handleError);
    }

    remove(identifier: number): Observable<Boolean> {
        return this.http.delete(environment.STOCK_URL + "/" + identifier, this.generateOptions())
            .map((response) => response.status === 200)
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