import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import 'rxjs/add/operator/toPromise';
import {User} from "./user";

@Injectable()
export class DataService {

    private dataUrl: string = 'service/users';

    constructor(private http: Http) { }

    getUsers(): Promise<User[]> {
        return this.http.get(this.dataUrl)
            .toPromise()
            .then(responce => responce.json())
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }
}