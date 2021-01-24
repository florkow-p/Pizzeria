import { Component, Input, OnInit } from '@angular/core';
import { Meal } from 'src/app/model/menu/meal';

@Component({
  selector: 'app-menu-item',
  templateUrl: './menu-item.component.html',
  styleUrls: ['./menu-item.component.css']
})
export class MenuItemComponent implements OnInit {

  @Input() item: Meal | undefined;

  constructor() {}

  ngOnInit(): void {
  }

}
