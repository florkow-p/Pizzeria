import { Injectable } from '@angular/core';
import { MealRequest } from '../model/meal-request';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private mealRequests: MealRequest[] = [];

  constructor() { }

  public addMeal(mealRequest: MealRequest) {
    this.mealRequests.push(mealRequest);
  }

  public getMealRequests() {
    return this.mealRequests;
  }

  public clear() {
    this.mealRequests = [];
  }
}
