import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import 'rxjs/add/operator/toPromise';
import {User} from "../model/user";
import {environment} from "../constants/environment";

@Injectable()
export class UserService {

    constructor(private http: Http) { }

    getUsers(): Promise<User[]> {
        return this.http.get(environment.USER_URL + environment.ALL_USERS_URL)
            .toPromise()
            .then(responce => responce.json())
            .catch(this.handleError);
    }

    getUserByUsername(inputText: string): Promise<User> {
        return this.http.get(environment.USER_URL + '/' + inputText)
            .toPromise()
            .then(responce => responce.json())
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}