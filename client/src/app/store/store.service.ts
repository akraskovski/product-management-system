import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs";
import {api} from "../constants/api";

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
}