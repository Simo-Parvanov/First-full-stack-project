import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CartItem} from 'src/app/components/models/cart-item';
import {map} from 'rxjs/operators';
import {ProductModel} from 'src/app/components/models/product-model';

const cartURL = 'http://localhost:8080/cart/';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { }

  getCartItem(): Observable<CartItem[]>{
    return this.http.get<CartItem[]>(cartURL).pipe(
      map((result: any[]) => {
        const cartItems: [] = [];

        for (const item of result) {
          let productExists = false;

          for (const i in cartItems) {
            if (cartItems[i].productId === item.product.id) {
              cartItems[i].quantity++;
              productExists = true;
            }
          }

          if (!productExists) {
            cartItems.push(new CartItem(item.id, item.product));
          }
        }
        return  cartItems;
      })
    )
  }
  addProductToCart(product: ProductModel): Observable<any>{
    return this.http.post(cartURL, {product});
  }
}
