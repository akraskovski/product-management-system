export class Product{
    id: number;
    name: string;
    cost: number;
    type: string;

    constructor(name: string, cost: number, type: string) {
        this.name = name;
        this.cost = cost;
        this.type = type;
    }
}