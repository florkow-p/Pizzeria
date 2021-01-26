import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Order } from 'src/app/model/order/order';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html',
  styleUrls: ['./order-history.component.css']
})
export class OrderHistoryComponent implements OnInit, OnDestroy {

  subscription: Subscription | undefined;

  orderId: string = '';
  order: Order | undefined;

  constructor(private orderService: OrderService) { }
  
  getOrder() {
    if(this.subscription != undefined) {
      this.subscription.unsubscribe();
    }

    if(this.orderId != undefined) {
      this.subscription = this.orderService.getOrder(this.orderId)
      .subscribe(order => this.order = order);
    }
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this.subscription?.unsubscribe();
  }
}
