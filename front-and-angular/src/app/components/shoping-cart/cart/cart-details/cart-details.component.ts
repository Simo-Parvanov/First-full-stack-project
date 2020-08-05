import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cart-details',
  templateUrl: './cart-details.component.html',
  styleUrls: ['./cart-details.component.css']
})
export class CartDetailsComponent implements OnInit {
  shoppingCart = [];
  productTotalSum = 0;
  shippingSum = 0;
  totalAmount = 0;
  constructor() { }

  ngOnInit(): void {
    this.productDisplay();
  }

  productDisplay(){
    const cartList = window.sessionStorage.getItem('cartItem');
    if (cartList != null){
      this.shoppingCart = JSON.parse(cartList);
    }
    this.totalSum();
    this.shipping();
    this.TotalAmountToPay();
  }

  totalSum(){
    this.productTotalSum = 0;
    this.shoppingCart.forEach(item => {
      this.productTotalSum += (item.quantity * item.price)
    })
  }

  shipping(){
    if (this.productTotalSum < 1000){
      this.shippingSum = 10;
    }
  }
  TotalAmountToPay(){
    this.totalAmount = this.productTotalSum + this.shippingSum;
  }
}
