import {Injectable} from "@angular/core";
import {Http, Headers, RequestOptions, Response} from "@angular/http";
import {User} from "../model/user";
import {environment} from "../constants/environment";

@Injectable()
export class LoginService {

    private currentUser: User;

    constructor(private http: Http) {
    }

    login(user: User) {
        this.currentUser = user;
        let body = JSON.stringify({username: user.username, password: user.password});
        let headers = new Headers({'Content-Type': 'application/json'});
        let options = new RequestOptions({headers: headers});

        return this.http.post(environment.LOGIN_URL, body, options)
            .map((response: Response) => {
                let token = response.json() && response.json().token;
                if (token) {
                    this.currentUser.token = token;
                    this.currentUser.roles = ['ROLE_ADMIN', 'ROLE_STOCK_MANAGER'];
                    alert('Welcome, ' + this.currentUser.username);
                    localStorage.setItem(environment.USER_KEY, JSON.stringify(this.currentUser));
                    return true;
                } else {
                    return false;
                }
            });
    }

    logout(): void {
        //this.token = null;
        this.currentUser = null;
        localStorage.removeItem(environment.USER_KEY);
    }

    get userRoles() : Array<string> {
        const user = JSON.parse(localStorage.getItem(environment.USER_KEY));
        return user ? user.roles : [];
    }
}