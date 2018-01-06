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
}