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
    createDate?: Date;
    authority: Authority;
    token: string;
}