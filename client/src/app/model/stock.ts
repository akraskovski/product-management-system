import {Product} from "./product";
export class Stock {

    id: number;
    specialize: string;
    address: string;
    phone: number;
    square: number;
    productList: Product[];

    constructor(specialize: string, address: string, phone: number, square: number, productList: Product[]) {
        this.specialize = specialize;
        this.productList = productList;
        this.address = address;
        this.phone = phone;
        this.square = square;
    }
}