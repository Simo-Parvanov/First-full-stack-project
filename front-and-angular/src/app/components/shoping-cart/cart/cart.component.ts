import {Component, OnInit} from '@angular/core';
import {MessengerService} from 'src/app/services/messenger.service';
import {ProductModel} from 'src/app/components/models/product-model';



@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartItem = [];
  totalSum = 0;

  constructor(private msg: MessengerService) {
  }

  ngOnInit() {
    this.handleSubscription()
    this.loadCartItems()
  }

  handleSubscription() {
    this.msg.getMsg().subscribe((product: ProductModel) => {

      const cartList = window.sessionStorage.getItem('cartItem');
        if (cartList != null){
          this.cartItem = JSON.parse(cartList);
        }

        let productExists = false;

        for (const i in this.cartItem) {
          if (this.cartItem[i].productId === product.id) {
            this.cartItem[i].quantity++;
            productExists = true;
          }
        }

        if (!productExists) {
          this.cartItem.push({
            productId: product.id,
            name: product.name,
            quantity: 1,
            price: product.price,
            priceOld: product.priceOld,
            discount: product.discount,
            image: product.image
          });
        }
        window.sessionStorage.setItem('cartItem', JSON.stringify(this.cartItem))
      this.loadCartItems()
    })
  }

  loadCartItems() {
    if (window.sessionStorage.getItem('cartItem') != null){
      const ppp = window.sessionStorage.getItem('cartItem');
      this.cartItem = JSON.parse(ppp);
    }
    this.calcCartTotal();
  }
  calcCartTotal() {
    this.totalSum = 0;
    this.cartItem.forEach(item => {
      this.totalSum += (item.quantity * item.price)
    })
  }
}
