import { Injectable } from '@angular/core';
import { Meal } from '../model/menu/meal';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  menu: Meal[] = [];
  toppings: Meal[] = [];
  extras: Meal[] = [];
  baseIngredients: Meal[] = [];

  constructor() { }

}
