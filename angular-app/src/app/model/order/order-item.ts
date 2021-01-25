import { Meal } from "../menu/meal";

export class OrderItem {
    meal: Meal;
    quantity: number;

    constructor(meal: Meal, quantity: number) {
        this.meal = meal;
        this.quantity = quantity;
    }
}
