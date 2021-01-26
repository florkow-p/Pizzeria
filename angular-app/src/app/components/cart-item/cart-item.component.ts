import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MealRequest } from 'src/app/model/meal-request';
import { Meal } from 'src/app/model/menu/meal';
import { Order } from 'src/app/model/order/order';
import { OrderItem } from 'src/app/model/order/order-item';
import { CartService } from 'src/app/services/cart.service';
import { SendOrderDialogComponent } from '../send-order-dialog/send-order-dialog.component';

@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.css']
})
export class CartItemComponent implements OnInit {

  mealRequests: MealRequest[] = [];
  orderItems: OrderItem[] = [];

  constructor(private cartService: CartService, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.mealRequests = this.cartService.getMealRequests();
    this.orderItems = this.cartService.getOrderItems();
  }

  getPrice() {
    return this.cartService.getPrice();
  }

  clearCart() {
    this.cartService.clear();
    this.ngOnInit();
  }

  payForOrderDialog() {
    if(this.orderItems == undefined || this.orderItems?.length == 0){
      return;
    }

    this.dialog.open(SendOrderDialogComponent, {
      data: {
        orderItems: this.orderItems,
        mealRequests: this.mealRequests
      }
    }).afterClosed().subscribe(order => {
      if(order?.orderSend) {
        this.clearCart();
      }
    });
  }
}
