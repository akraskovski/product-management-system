import {Authority} from "./authority";
export class User {
    id: string;
    username: string;
    password: string;
    authorities: Authority[];
    token: string;

    constructor(username: string, password: string) {
        this.username = username;
        this.password = password;
    }
}