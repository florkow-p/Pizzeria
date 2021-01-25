import { Injectable } from '@angular/core';
import { MealRequest } from '../model/meal-request';
import { Order } from '../model/order/order';
import { OrderItem } from '../model/order/order-item';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private price: number = 0;

  private mealRequests: MealRequest[] = [];
  private orderItems: OrderItem[] = [];

  constructor() { }

  public addMeal(mealRequest: MealRequest, price: number) {
    this.mealRequests.push(mealRequest);
    this.price += price;
  }

  public addOrderItem(orderItem: OrderItem) {
    this.orderItems.push(orderItem);
  }

  public getMealRequests() {
    return this.mealRequests;
  }

  public getOrderItems() {
    return this.orderItems;
  }

  public getPrice() {
    return this.price;
  }

  public clear() {
    this.mealRequests = [];
    this.orderItems = [];
    this.price = 0;
  }
}
