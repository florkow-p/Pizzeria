export class Meal {
    id: number;
    name: String;
    mealType: String
    price: number;
    allowedBaseIngredient: boolean;

    toppings: any[] = [];
    baseIngredient: any;
    extras: any;

    constructor(id: number, name: String, mealType: String, price: number, allowedBaseIngredient: boolean) {
        this.id = id;
        this.name = name;
        this.mealType = mealType;
        this.price = price;
        this.allowedBaseIngredient = allowedBaseIngredient;
    }
}
