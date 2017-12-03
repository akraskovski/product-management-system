import {Injectable} from "@angular/core";
import {Headers, Http, RequestOptions, Response} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {api} from "../constants/api";
import {AuthorityWorker} from "../common/authority-worker";

@Injectable()
export class StockService {

    constructor(private http: Http) {
    }

    getAllManagers(): Observable<any> {
        const headers: Headers = new Headers({'x-auth-token': AuthorityWorker.getCurrentUser().token});
        return this.http.get(api.USER + "/role/ROLE_ADMIN", new RequestOptions({headers: headers}))
            .map((response: Response) => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error("Response status: " + response.status);
                }
            });
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

    updateProducts(stockId: string, productId: string): Observable<any> {
        const headers: Headers = new Headers({'x-auth-token': AuthorityWorker.getCurrentUser().token});
        let params: URLSearchParams = new URLSearchParams();
        params.set('stockId', stockId);
        params.set('productId', productId);
        return this.http.put(api.STOCK + "/stock/product", new RequestOptions({
            headers: headers,
            params: URLSearchParams
        }))
            .map((response: Response) => {
                if (response.status != 204) {
                    throw new Error("Response status: " + response.status);
                }
            });
    }
}