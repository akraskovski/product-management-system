import {Product} from "./product";

export class Stock {
    id: string;
    specialize: string;
    open_time?: any;
    close_time?: any;
    address?: string;
    phone?: string;
    square?: number;
    productList: Product[];
    managerId?: string;

    constructor(specialize: string, address: string, phone: string, square: number) {
        this.specialize = specialize;
        this.address = address;
        this.phone = phone;
        this.square = square;
    }
}