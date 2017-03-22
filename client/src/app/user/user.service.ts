import {Injectable} from "@angular/core";
import {Http, Response, Headers, RequestOptions} from "@angular/http";
import "rxjs/add/operator/toPromise";
import {User} from "../model/user";
import {environment} from "../constants/environment";
import {Observable} from "rxjs";
import {AuthorizationService} from "../authorization/authorization.service";
import {Authority} from "../model/authority";

@Injectable()
export class UserService {

    constructor(private http: Http) { }

    loadAll(): Observable<User[]> {
        return this.http.get(environment.USER_URL, this.generateOptions())
            .map(responce => responce.json())
            .catch(UserService.handleError);
    }

    loadAllAuthorities(): Observable<Authority[]> {
        return this.http.get(environment.AUTHORITY_URL, this.generateOptions())
            .map(responce => responce.json())
            .catch(UserService.handleError);
    }

    loadById(identifier: number) {
        return this.http.get(environment.USER_URL + "/" + identifier, this.generateOptions())
            .map((response: Response) => response.json())
            .catch(UserService.handleError);
    }

    create(user: User) {
        return this.http.post(environment.USER_URL, user, this.generateOptions())
            .map((response: Response) => response.status === 201)
            .catch(UserService.handleError);
    }

    update(user: User) {
        return this.http.put(environment.USER_URL, user, this.generateOptions())
            .map((response: Response) => response.status === 200)
            .catch(UserService.handleError);
    }

    remove(identifier: number): Observable<Boolean> {
        return this.http.delete(environment.USER_URL + "/" + identifier, this.generateOptions())
            .map((response) => response.status === 200)
            .catch(UserService.handleError);
    }

    private generateOptions(): RequestOptions {
        const headers = new Headers({
            'Content-Type': 'application/json',
            'x-auth-token': AuthorizationService.getCurrentUser().token
        });
        return new RequestOptions({headers: headers});
    }

    private static handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}