import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { Meal } from 'src/app/model/menu/meal';
import { OrderRequest } from 'src/app/model/order-request';
import { MenuService } from 'src/app/services/menu.service';
import { OrderService } from 'src/app/services/order.service';
import { AddToCartDialogComponent } from '../add-to-cart-dialog/add-to-cart-dialog.component';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit, OnDestroy {

  private orderRequest: any;
  private menu: Meal[] = [];
  private baseIngredients: Meal[] = [];
  private toppings: Meal[] = [];
  private extras: Meal[] = [];

  private subscriptions: Subscription[] = [];

  constructor(private orderService: OrderService, private menuService: MenuService,private dialog: MatDialog) {}

  ngOnInit(): void {
    var subscription = this.orderService.getMenu().subscribe((menu: Meal[]) => {
      this.menu = menu;
      this.prepareAddings();
    });
    this.subscriptions.push(subscription);
  }

  prepareAddings(): void {
    this.menu.forEach(item => {
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
    
    this.menuService.menu = this.menu;
    this.menuService.toppings = this.toppings;
    this.menuService.extras = this.extras;
    this.menuService.baseIngredients = this.baseIngredients;
  }

  getMenu() {
    return this.menu;
  }

  getToppings() {
    return this.toppings;
  }

  openDialog(meal: Meal) {
    this.dialog.open(AddToCartDialogComponent, {
      data: {
        meal: meal,
        baseIngredients: this.baseIngredients,
        extras: this.extras,
        toppings: this.toppings
      }
    })
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }
}
