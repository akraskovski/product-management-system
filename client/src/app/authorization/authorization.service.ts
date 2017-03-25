import {Injectable} from "@angular/core";
import {Headers, Http, RequestOptions, Response} from "@angular/http";
import {User} from "../model/user";
import {Observable} from "rxjs";
import {api} from "../constants/api";
import {keys} from "../constants/keys";

@Injectable()
export class AuthorizationService {

    private currentUser: User;

    constructor(private http: Http) {
    }

    login(user: User): Observable<Boolean> {
        const body = JSON.stringify({username: user.username, password: user.password});
        const options = new RequestOptions({headers: new Headers({'Content-Type': 'application/json'})});
        return this.http.post(api.LOGIN, body, options)
            .map(this.extractData)
            .catch(AuthorizationService.handleError);
    }

    logout(): void {
        this.currentUser = null;
        localStorage.removeItem(keys.USER_KEY);
    }

    private extractData(response: Response) {
        const token = response.json().token;
        const user = response.json().user;
        if (token && user) {
            this.currentUser = user;
            this.currentUser.token = token;
            localStorage.setItem(keys.USER_KEY, JSON.stringify(this.currentUser));
            return true;
        }
        return false;
    }

    private static handleError(error: Response | any) {
        let errMsg: string;
        if (error instanceof Response) {
            const body = error.json() || '';
            const err = body.error || JSON.stringify(body);
            errMsg = `${error.status} - ${error.statusText}`;
            errMsg += `${err}`;
        } else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error(errMsg);
        return Observable.throw(errMsg);
    }

    static getCurrentUser() {
        return JSON.parse(localStorage.getItem(keys.USER_KEY));
    }
}