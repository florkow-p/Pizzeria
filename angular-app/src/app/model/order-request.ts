import { MealRequest } from "./meal-request";
import { OrderDetails } from "./order-details";

export class OrderRequest {
    orderDetails: OrderDetails;
    mealRequests: MealRequest[];

    constructor(orderDetails: OrderDetails, mealRequests: MealRequest[]) {
        this.orderDetails = orderDetails;
        this.mealRequests = mealRequests;
    }
}
