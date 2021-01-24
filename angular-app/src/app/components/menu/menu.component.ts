import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Meal } from 'src/app/model/menu/meal';
import { OrderRequest } from 'src/app/model/order-request';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit, OnDestroy {

  private orderRequest: any;
  private meal: Meal[] = [];
  private baseIngredients: Meal[] = [];
  private toppings: Meal[] = [];
  private extras: Meal[] = [];

  private subscriptions: Subscription[] = [];

  constructor(private orderService: OrderService) {}

  ngOnInit(): void {
    var subscription = this.orderService.getMenu().subscribe((menu: Meal[]) => {
      this.meal = menu;
      this.prepareAddings();
    });
    this.subscriptions.push(subscription);
  }

  prepareAddings(): void {
    this.meal.forEach(item => {
      if(item.mealType.match('EXTRAS')) {
        this.extras.push(item);
      }
      if(item.mealType.match('TOPPING')) {
        this.toppings.push(item);
      }
      if(item.mealType.match('BASE_INGREDIENT')) {
        this.baseIngredients.push(item);
      }
    });
  }

  getMeal() {
    return this.meal;
  }

  getToppings() {
    return this.toppings;
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

  openDialog(meal: Meal) {
    
  }
  
}
