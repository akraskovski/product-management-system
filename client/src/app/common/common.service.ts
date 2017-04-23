import {Injectable} from "@angular/core";
import {Headers, Http, RequestOptions, Response} from "@angular/http";
import {AuthorityWorker} from "./authority-worker";
import {Observable} from "rxjs";

@Injectable()
export class CommonService {

    constructor(private http: Http) {
    }

    loadAll(URL: string): Observable<any> {
        return this.http.get(URL, this.generateOptions())
            .map((response: Response) => {
                if (response.status != 200) {
                    throw new Error('Error while loading all entities! code status: ' + response.status);
                } else {
                    return response.json();
                }
            })
    }

    loadAllUnauthorized(URL: string): Observable<any> {
        return this.http.get(URL)
            .map(response => {
                if (response.status != 200) {
                    throw new Error('Error while loading all entities! code status: ' + response.status);
                } else {
                    return response.json();
                }
            })
    }

    loadById(URL: string, identifier: number): Observable<any> {
        return this.http.get(URL + "/" + identifier, this.generateOptions())
            .map((response: Response) => {
                if (response.status != 200) {
                    throw new Error('Entity not found! code status: ' + response.status);
                } else {
                    return response.json();
                }
            })
    }

    loadByIdUnauthorized(URL: string, identifier: number): Observable<any> {
        return this.http.get(URL + "/" + identifier)
            .map((response: Response) => {
                if (response.status != 200) {
                    throw new Error('Entity not found! code status: ' + response.status);
                } else {
                    return response.json();
                }
            })
    }

    loadByName(URL: string, name: string): Observable<any> {
        return this.http.get(URL + "/name/" + name, this.generateOptions())
            .map((response: Response) => {
                if (response.status != 200) {
                    throw new Error('Entity not found! code status: ' + response.status);
                } else {
                    return response.json();
                }
            })
    }

    create(URL: string, body: any): Observable<any> {
        return this.http.post(URL, body, this.generateOptions())
            .map((response: Response) => {
                if (response.status != 201) {
                    throw new Error('Exception: ' + response.status);
                }
            })
    }

    update(URL: string, body: any): Observable<any> {
        return this.http.put(URL, body, this.generateOptions())
            .map((response: Response) => {
                if (response.status != 200) {
                    throw new Error('Exception: ' + response.status);
                }
            })
    }

    remove(URL: string, identifier: number): Observable<any> {
        return this.http.delete(URL + "/" + identifier, this.generateOptions())
            .map((response) => {
                if (response.status != 200) {
                    throw new Error('Exception: ' + response.status);
                }
            })
    }

    private generateOptions(): RequestOptions {
        const headers = new Headers({
            'Content-Type': 'application/json',
            'x-auth-token': AuthorityWorker.getCurrentUser().token
        });
        return new RequestOptions({headers: headers});
    }
}