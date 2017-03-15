import {Injectable} from "@angular/core";
import {Http, Headers, RequestOptions} from "@angular/http";
import {environment} from "../constants/environment";
import {Product} from "../model/product";

@Injectable()
export class ProductService {

    constructor(private http: Http) {
    }

    loadAll(): Promise<Product[]> {
        return this.http.get(environment.PRODUCT_URL)
            .toPromise()
            .then(responce => responce.json())
            .catch(this.handleError);
    }

    loadByName(name: string): Promise<Product> {
        return this.http.get(environment.PRODUCT_URL + name)
            .toPromise()
            .then(responce => responce.json())
            .catch(this.handleError);
    }

    add(product: Product) {
        let body = JSON.stringify({name: product.name, cost: product.cost, type: product.type});
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});
        return this.http.put(environment.PRODUCT_URL, body, options).map(() => {
            return true;
        });
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}