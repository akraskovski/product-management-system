import {Authority} from "./authority";

export class User {
    id: string;
    username: string;
    password: string;
    firstName?: string;
    lastName?: string;
    avatar?: string;
    phone?: string;
    email?: string;
    createDate?: any;
    authorities: Authority[];
    token: string;

    constructor(username: string, password: string) {
        this.username = username;
        this.password = password;
    }
}