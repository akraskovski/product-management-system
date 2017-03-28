import {Injectable} from "@angular/core";
import {Headers, Http, RequestOptions, Response} from "@angular/http";
import "rxjs/add/operator/toPromise";
import {AuthorityWorker} from "./authority-worker";
import {Observable} from "rxjs";

@Injectable()
export class CommonService {

    constructor(private http: Http) {
    }

    loadAll(URL: string): Observable<any> {
        return this.http.get(URL, this.generateOptions())
            .map((response: Response) => response.json())
            .catch(CommonService.handleError);
    }

    loadAllUnauthorized(URL: string): Observable<any> {
        return this.http.get(URL)
            .map(response => response.json())
            .catch(CommonService.handleError);
    }

    loadById(URL: string, identifier: number): Observable<any> {
        return this.http.get(URL + "/" + identifier, this.generateOptions())
            .map((response: Response) => response.json())
            .catch(CommonService.handleError);
    }

    loadByName(URL: string, name: string): Observable<any> {
        return this.http.get(URL + "/name/" + name)
            .map((response: Response) => response.json())
            .catch(CommonService.handleError);
    }

    create(URL: string, body: any): Observable<any> {
        return this.http.post(URL, body, this.generateOptions())
            .map((response: Response) => response.status === 201)
            .catch(CommonService.handleError);
    }

    update(URL: string, body: any): Observable<any> {
        return this.http.put(URL, body, this.generateOptions())
            .map((response: Response) => response.status === 200)
            .catch(CommonService.handleError);
    }

    remove(URL: string, identifier: number): Observable<any> {
        return this.http.delete(URL + "/" + identifier, this.generateOptions())
            .map((response) => response.status === 200)
            .catch(CommonService.handleError);
    }

    private generateOptions(): RequestOptions {
        const headers = new Headers({
            'Content-Type': 'application/json',
            'x-auth-token': AuthorityWorker.getCurrentUser().token
        });
        return new RequestOptions({headers: headers});
    }

    private static handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}