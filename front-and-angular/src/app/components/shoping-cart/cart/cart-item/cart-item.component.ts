import {Component, Input, OnInit} from '@angular/core';
import {CartItem} from 'src/app/components/models/cart-item';

@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.css']
})
export class CartItemComponent implements OnInit {
  @Input() cartItemModel: CartItem;
  constructor() { }

  ngOnInit(): void {
  }

}
