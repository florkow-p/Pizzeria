import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { MenuComponent } from './components/menu/menu.component';
import { OrderHistoryComponent } from './components/order-history/order-history.component';

const routes: Routes = [
  { 
    path: '',
    component: HomeComponent
  },
  {
    path: 'menu',
    component: MenuComponent
  },
  {
    path: 'order',
    component: OrderHistoryComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
