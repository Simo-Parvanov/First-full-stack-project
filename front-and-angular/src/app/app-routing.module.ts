import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {RegisterComponent} from './components/register/register.component';
import {LoginComponent} from './components/login/login.component';
import {ShoppingCartComponent} from './components/shoping-cart/shopping-cart.component';
import {PageNotFoundComponent} from './components/shared/page-not-found/page-not-found.component';
import {ProfileComponent} from './components/profile/profile.component';
import {CartDetailsComponent} from 'src/app/components/shoping-cart/cart/cart-details/cart-details.component';
import {AdministrationComponent} from 'src/app/components/administration/administration.component';
import {ProductDetailComponent} from 'src/app/components/product-detail/product-detail.component';



const routes: Routes = [
  {path: '', redirectTo: '/shop', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'cart', component: CartDetailsComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'shop', component: ShoppingCartComponent, data : {some_data : 'some value'}},
  {path: 'profile', component: ProfileComponent},
  {path: 'administrator', component: AdministrationComponent},
  {path: 'product/:id', component: ProductDetailComponent},
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
