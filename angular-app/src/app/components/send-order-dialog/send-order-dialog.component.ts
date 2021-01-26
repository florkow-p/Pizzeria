import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Subscription } from 'rxjs';
import { MealRequest } from 'src/app/model/meal-request';
import { OrderDetails } from 'src/app/model/order-details';
import { OrderRequest } from 'src/app/model/order-request';
import { OrderItem } from 'src/app/model/order/order-item';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-send-order-dialog',
  templateUrl: './send-order-dialog.component.html',
  styleUrls: ['./send-order-dialog.component.css']
})
export class SendOrderDialogComponent implements OnInit, OnDestroy {

  private subscription: Subscription | undefined;

  orderItems: OrderItem[] = [];
  mealRequests: MealRequest[] = [];
  name: string = '';
  comment: string = '';

  constructor(@Inject(MAT_DIALOG_DATA) public data: any, private orderService: OrderService) { }

  sendOrder() {
    var orderDetails: OrderDetails = new OrderDetails(this.name, this.comment);
    
    this.subscription = this.orderService.postOrder(new OrderRequest(orderDetails, this.mealRequests))
    .subscribe();
  }

  ngOnInit(): void {
    this.orderItems = this.data.orderItems;
    this.mealRequests = this.data.mealRequests;
  }

  ngOnDestroy(): void {
    this.subscription?.unsubscribe();
  }
}
