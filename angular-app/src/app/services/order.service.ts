import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Meal } from '../model/menu/meal';
import { OrderRequest } from '../model/order-request';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private url = 'http://localhost:20000/api/meal/v1/menu';

  constructor(private httpClient: HttpClient) { }

  public getMenu() {
    return this.httpClient.get<Meal[]>(this.url);
  }

  public postOrder(orderRequest: OrderRequest) {
    return this.httpClient.post(this.url, orderRequest);
  }
}
