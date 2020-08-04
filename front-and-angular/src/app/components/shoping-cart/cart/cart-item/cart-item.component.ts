import {Component, Input, OnInit} from '@angular/core';
import {ProductCart} from 'src/app/components/models/product-cart';

@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.css']
})
export class CartItemComponent implements OnInit {
  @Input() cartItemModel: ProductCart;
  constructor() { }

  ngOnInit(): void {
  }

}
