import {Injectable} from "@angular/core";
import {Http, RequestOptions, Response} from "@angular/http";
import {Observable} from "rxjs";
import {api} from "../constants/api";
import {CommonService} from "../common/common.service";

@Injectable()
export class StoreService {

    private static manageUrl: string = api.STORE + "/stock-manage";
    private static RequestBody = class {
        storeId: string;
        stockId: string;

        constructor(storeId: string, stockId: string) {
            this.storeId = storeId;
            this.stockId = stockId;
        }
    };

    constructor(private http: Http) {
    }

    getStocks(id: string): Observable<any> {
        return this.http.get(api.STORE + "/" + id + "/stock-manage")
            .map((response: Response) => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error("Response status: " + response.status);
                }
            });
    }

    addStock(storeId: string, stockId: string): Observable<any> {
        const body = new StoreService.RequestBody(storeId, stockId);
        const requestOptions: RequestOptions = CommonService.generateOptions();
        return this.http.put(StoreService.manageUrl, body, requestOptions).map((response: Response) => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Response status: " + response.status);
            }
        })
    }

    removeStock(storeId: string, stockId: string): Observable<any> {
        const requestOptions: RequestOptions = CommonService.generateOptions();
        requestOptions.body = new StoreService.RequestBody(storeId, stockId);
        return this.http.delete(StoreService.manageUrl, requestOptions).map((response: Response) => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Response status: " + response.status);
            }
        })
    }
}