import {Product} from "./product";
export class Stock {

    id: number;
    specialize: string;
    productList: Product[];

    constructor(specialize: string, productList: Product[]) {
        this.specialize = specialize;
        this.productList = productList;
    }
}