import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  shoppingCart = [];
  productTotalSum = 0;
  shippingSum = 0;
  totalAmount = 0;
  login: boolean;
  constructor() { }

  ngOnInit(): void {
    this.productDisplay();
    this.isLogin();
    console.log(this.login)
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
  isLogin(){
    this.login = !!sessionStorage.getItem('user-auth');
  }

}
