import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import 'rxjs/add/operator/toPromise';
import {User} from "./model/user";

@Injectable()
export class UserService {

    private usersUrl: string = 'service/users';

    constructor(private http: Http) { }

    getUsers(): Promise<User[]> {
        return this.http.get(this.usersUrl)
            .toPromise()
            .then(responce => responce.json())
            .catch(this.handleError);
    }

    getUserByName(name: string): Promise<User> {
        return this.http.get(this.usersUrl + '/' + name)
            .toPromise()
            .then(responce => responce.json())
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}