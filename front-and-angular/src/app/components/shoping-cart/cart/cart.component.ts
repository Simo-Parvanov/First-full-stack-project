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
    this.msg.getMsg().subscribe((product: ProductModel) => {
      this.addProductToCart(product)
    })
  }

  addProductToCart(product: ProductModel) {
   let productExists = false;

    for (const i in this.cartItem) {
      if (this.cartItem[i].id === product.id) {
        this.cartItem[i].quantity++;
        productExists = true;
      }
    }

   if (!productExists){
     this.cartItem.push({
       id: product.id,
       productName: product.name,
       quantity: 1,
       price: product.price,
       url: product.image
     });
   }

    this.totalSum = 0;
    this.cartItem.forEach(item => {
      this.totalSum += (item.quantity * item.price)
    })
  }
}
