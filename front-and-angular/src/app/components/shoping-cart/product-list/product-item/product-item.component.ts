import {Component, Input, OnInit} from '@angular/core';
import {ProductModel} from 'src/app/components/models/product-model';
import { MessengerService } from 'src/app/services/messenger.service';
import {CartService} from 'src/app/services/cart.service';

@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.css']
})
export class ProductItemComponent implements OnInit {
  @Input() productItem: ProductModel;

  constructor(private msg: MessengerService, private cartService: CartService) { }

  ngOnInit(): void {
  }

  handleAddToCart(){
    this.cartService.addProductToCart(this.productItem).subscribe(()=>{
      this.msg.sendMsg(this.productItem)
    })
  }

  // getKeys(obj) {
  //   return Object.values(obj);
  // }
}
