import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {environment} from "../constants/environment";
import {Product} from "../model/product";

@Injectable()
export class ProductService {

    constructor(private http: Http) {
    }

    get products(): Promise<Product[]> {
        return this.http.get(environment.PRODUCT_URL + environment.ALL_URL)
            .toPromise()
            .then(responce => responce.json())
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}