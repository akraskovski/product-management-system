import {Injectable} from "@angular/core";
import {Http, RequestOptions, Response} from "@angular/http";
import {Observable} from "rxjs";
import {api} from "../constants/api";
import {CommonService} from "../common/common.service";

@Injectable()
export class StoreService {

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
        const url = api.STORE + "/stock-manage";
        const requestOptions: RequestOptions = CommonService.generateOptions();
        const params = new URLSearchParams();
        params.append("storeId", storeId);
        params.append("stockId", stockId);
        requestOptions.merge(params);
        return this.http.put(url, requestOptions).map((response: Response) => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error("Response status: " + response.status);
                }
        })
    }
}