import { OrderDetails } from "../order-details";
import { OrderItem } from "./order-item";

export class Order {
    id: string;
    orderItems: OrderItem[];
    orderDetails: OrderDetails;
    price: number;

    constructor(id: string, orderItems: OrderItem[], orderDetails: OrderDetails, price: number) {
        this.id = id;
        this.orderItems = orderItems;
        this.orderDetails = orderDetails;
        this.price = price;
    }
}
