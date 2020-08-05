import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.css']
})
export class CartDetailsComponent implements OnInit {
  shoppingCart = [];
  constructor() { }

  ngOnInit(): void {
    const cartList = window.sessionStorage.getItem('cartItem');
    if (cartList != null){
      this.shoppingCart = JSON.parse(cartList);
    }
  }

}
