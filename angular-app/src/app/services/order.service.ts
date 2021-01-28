import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Meal } from '../model/menu/meal';
import { OrderRequest } from '../model/order-request';
import { Order } from '../model/order/order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private httpClient: HttpClient) { }

  public getMenu() {
    return this.httpClient.get<Meal[]>(environment.url);
  }

  public getOrder(orderId: string) {
    return this.httpClient.get<Order>(environment.orderHistoryUrl + orderId);
  }

  public postOrder(orderRequest: OrderRequest) {
    return this.httpClient.post(environment.url, orderRequest);
  }
}
