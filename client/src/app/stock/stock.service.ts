import {Injectable} from "@angular/core";
import {Headers, Http, RequestOptions, Response} from "@angular/http";
import {Observable} from "rxjs";
import {api} from "../constants/api";
import {AuthorityWorker} from "../common/authority-worker";
import {CommonService} from "../common/common.service";

@Injectable()
export class StockService {

    constructor(private http: Http) {
    }

    getStockManagers(): Observable<any> {
        const headers: Headers = new Headers({'x-auth-token': AuthorityWorker.getCurrentUser().token});
        return this.http.get(api.USER + "/role/ROLE_STOCK_MANAGER", new RequestOptions({headers: headers}))
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

    addProductToStock(stockId: string, productId: string): Observable<any> {
        const url: string = api.STOCK + "/" + stockId + "/product/" + productId;
        return this.http.put(url, null, CommonService.generateOptions())
            .map((response: Response) => {
                if (response.status != 204) {
                    throw new Error('Entity not found! code status: ' + response.status);
                }
            });
    }
}