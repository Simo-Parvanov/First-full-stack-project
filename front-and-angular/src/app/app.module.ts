import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/shared/header/header.component';
import { FooterComponent } from './components/shared/footer/footer.component';
import { NavComponent } from './components/shared/nav/nav.component';
import { ShoppingCartComponent } from './components/shoping-cart/shopping-cart.component';
import { FiltersComponent } from './components/shoping-cart/filters/filters.component';
import { ProductListComponent } from './components/shoping-cart/product-list/product-list.component';
import { CartComponent } from './components/shoping-cart/cart/cart.component';
import { CartItemComponent } from './components/shoping-cart/cart/cart-item/cart-item.component';
import { ProductItemComponent } from './components/shoping-cart/product-list/product-item/product-item.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ProfileComponent } from './components/profile/profile.component';
import { UploadImageComponent } from './image/upload-image/upload-image.component';
import { ListImageComponent } from './image/list-image/list-image.component';

import { authInterceptorProviders } from 'src/app/helpers/auth.interceptor';
import { FileUploadModule } from 'ng2-file-upload';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgxSpinnerModule } from 'ngx-spinner';
import { PageNotFoundComponent } from './components/shared/page-not-found/page-not-found.component';
import { CartDetailsComponent } from './components/shoping-cart/cart/cart-details/cart-details.component';
import { AdministrationComponent } from './components/administration/administration.component';
import { ProductDetailComponent } from './components/product-detail/product-detail.component';



@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    NavComponent,
    ShoppingCartComponent,
    FiltersComponent,
    ProductListComponent,
    CartComponent,
    CartItemComponent,
    ProductItemComponent,
    LoginComponent,
    RegisterComponent,
    ProfileComponent,
    UploadImageComponent,
    ListImageComponent,
    PageNotFoundComponent,
    CartDetailsComponent,
    AdministrationComponent,
    ProductDetailComponent,


  ],
    imports: [
      AppRoutingModule,
      HttpClientModule,
      BrowserModule,
      FormsModule,
      FileUploadModule,
      BrowserAnimationsModule,
      NgxSpinnerModule,
      RouterModule
    ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
