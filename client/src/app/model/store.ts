import {Stock} from "./stock";
export class Store {

    id: number;
    name: string;
    stockList: Stock[];

    constructor(name: string, stockList: Stock[]) {
        this.name = name;
        this.stockList = stockList;
    }
}