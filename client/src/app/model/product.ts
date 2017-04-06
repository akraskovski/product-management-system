export class Product{
    id: number;
    name: string;
    cost: number;
    type: string;
    details: string;

    constructor(name: string, cost: number, type: string, details: string) {
        this.name = name;
        this.cost = cost;
        this.type = type;
        this.details = details;
    }
}