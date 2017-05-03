import {Stock} from "./stock";
export class Store {
    id: number;
    name: string;
    details?: string;
    address?: string;
    phone?: number;
    skype?: string;
    discounts?: boolean;
    mail?: string;
    logo?: string;
    stockList: Stock[];
}