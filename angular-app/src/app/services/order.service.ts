import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Meal } from '../model/menu/meal';
import { OrderRequest } from '../model/order-request';
import { Order } from '../model/order/order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private url = 'http://localhost:20000/api/meal/v1/menu';
  private orderHistoryUrl = 'http://localhost:20000/api/order/v1/history?id=';

  constructor(private httpClient: HttpClient) { }

  public getMenu() {
    return this.httpClient.get<Meal[]>(this.url);
  }

  public getOrder(orderId: string) {
    return this.httpClient.get<Order>(this.orderHistoryUrl + orderId);
  }

  public postOrder(orderRequest: OrderRequest) {
    return this.httpClient.post(this.url, orderRequest);
  }
}
