import {Product} from "./product";
export class Stock {
    id: number;
    specialize: string;
    address: string;
    phone: string;
    square: number;
    productList: Product[];

    constructor(specialize: string, address: string, phone: string, square: number, productList: Product[]) {
        this.specialize = specialize;
        this.address = address;
        this.phone = phone;
        this.square = square;
        this.productList = productList;
    }
}