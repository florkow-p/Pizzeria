import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MealRequest } from 'src/app/model/meal-request';
import { Meal } from 'src/app/model/menu/meal';
import { OrderItem } from 'src/app/model/order/order-item';
import { CartService } from 'src/app/services/cart.service';
import { MenuService } from 'src/app/services/menu.service';

@Component({
  selector: 'app-add-to-cart-dialog',
  templateUrl: './add-to-cart-dialog.component.html',
  styleUrls: ['./add-to-cart-dialog.component.css']
})
export class AddToCartDialogComponent implements OnInit {

  mealRequest: MealRequest;
  meal: any;
  quantity: number = 1;

  toppings: Meal[] = [];
  extras: Meal[] = [];
  baseIngredients: Meal[] = [];

  selectedToppings: number[] = [];
  selectedIngredient: any;
  selectedExtras: any;

  price: number = 0;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private menuService: MenuService, private cartService: CartService) { 
    this.mealRequest = new MealRequest();
  }

  addToCart() {
    this.mealRequest = new MealRequest();
    this.mealRequest.id = this.meal.id;
    this.mealRequest.quantity = this.quantity;
    this.mealRequest.toppings = this.selectedToppings;
    this.mealRequest.baseIngredientId = this.selectedIngredient?.id;
    this.mealRequest.extrasId = this.selectedExtras?.id;

    var orderItem = new OrderItem(this.meal, this.quantity);

    if(this.quantity > 0) {
      this.cartService.addMeal(this.mealRequest, this.calculatePrice());
      this.cartService.addOrderItem(orderItem);
    }
  }

  private calculatePrice(): number {
    this.price += this.meal.price;

    if(this.selectedExtras?.price > 0) {
      this.price += this.selectedExtras.price;
    }

    if(this.selectedIngredient?.price > 0) {
      this.price += this.selectedIngredient.price;
    }
  
    return this.price * this.quantity;
  }

  setToppings(topping: Meal, checked: boolean) {
    if(checked && !this.selectedToppings.includes(topping.id)) {
      this.selectedToppings.push(topping.id);
      this.price += topping.price;
    }

    if(!checked) {
      const index: number = this.selectedToppings.indexOf(topping.id);
      if (index !== -1) {
        this.selectedToppings.splice(index, 1);
        this.price -= topping.price;
      }
    }    
  }

  increase() {
    this.quantity++;
  }

  decrease() {
    if(this.quantity > 0) {
      this.quantity--;
    }
  }

  getQuantity() {
    return this.quantity;
  }

  ngOnInit(): void {
    this.meal = this.data?.meal;
    this.toppings = this.menuService.toppings;
    this.extras = this.menuService.extras;
    this.baseIngredients = this.menuService.baseIngredients;
  }
}
