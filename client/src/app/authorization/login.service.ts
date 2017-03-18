import {Injectable} from "@angular/core";
import {Http, Headers, RequestOptions, Response} from "@angular/http";
import {User} from "../model/user";
import {environment} from "../constants/environment";
import {Observable} from "rxjs";

@Injectable()
export class LoginService {

    private currentUser: User;

    constructor(private http: Http) {
    }

    login(user: User) {
        let body = JSON.stringify({username: user.username, password: user.password});
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});

        return this.http.post(environment.LOGIN_URL, body, options)
            .map((response: Response) => {
                let token = response.json() && response.json().token;
                let user = response.json() && response.json().user;
                if (token && user) {
                    this.currentUser = user;
                    this.currentUser.token = token;
                    localStorage.setItem(environment.USER_KEY, JSON.stringify(this.currentUser));
                    return true;
                } else {
                    return false;
                }
            })
            .catch(LoginService.handleError);
    }

    logout(): void {
        this.currentUser = null;
        localStorage.removeItem(environment.USER_KEY);
    }

    private static handleError(error: Response | any) {
        let errMsg: string;
        if (error instanceof Response) {
            const body = error.json() || '';
            const err = body.error || JSON.stringify(body);
            errMsg = `${error.status} - ${error.statusText} || ''} ${err}`;
        } else {
            errMsg = error.message ? error.message : error.toString();
        }
        console.error(errMsg);
        return Observable.throw(errMsg);
    }
}