import {Injectable} from "@angular/core";
import {Headers, Http, RequestOptions, Response} from "@angular/http";
import {Observable} from "rxjs";
import {api} from "../constants/api";
import {AuthorityWorker} from "../common/authority-worker";

@Injectable()
export class StockService {

    constructor(private http: Http) {
    }

    getStockProducts(id: string): Observable<any> {
        const headers: Headers = new Headers({'x-auth-token': AuthorityWorker.getCurrentUser().token});
        return this.http.get(api.STOCK + "/" + id + "/products", new RequestOptions({headers: headers}))
            .map((response: Response) => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error("Response status: " + response.status);
                }
            });

    }

    addProductToStock(stockId: string, productId: string): Observable<any> {
        const url: string = api.STOCK + "/" + stockId + "/product/" + productId;
        return this.http.put(url, StockService.generateOptions())
            .map((response: Response) => {
                if (response.status != 204) {
                    throw new Error("Response status: " + response.status);
                }
            });
    }

    static generateOptions(): RequestOptions {
        const headers = new Headers({
            'Content-Type': 'application/json',
            'x-auth-token': AuthorityWorker.getCurrentUser().token
        });
        return new RequestOptions({headers: headers});
    }
}