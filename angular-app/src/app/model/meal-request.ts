export class MealRequest {
    id: number;
    quantity: number;
    toppings: number[];
    extrasId: number;
    baseIngredientId: number;

    constructor(id: number, quantity: number, toppings: number[], extrasId: number, baseIngredientId: number) {        
        this.id = id;
        this.quantity = quantity;
        this.toppings = toppings;
        this.extrasId = extrasId;
        this.baseIngredientId = baseIngredientId;
    }
}
