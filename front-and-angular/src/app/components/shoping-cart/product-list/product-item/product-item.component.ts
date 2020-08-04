import {Component, Input, OnInit} from '@angular/core';
import {ProductModel} from 'src/app/components/models/product-model';
import { MessengerService } from 'src/app/services/messenger.service';

@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.css']
})
export class ProductItemComponent implements OnInit {
  @Input() productItem: ProductModel[];

  constructor(private msg: MessengerService) { }

  ngOnInit(): void {
  }

  handleAddToCart(){
    this.msg.sendMsg(this.productItem)
  }

  // getKeys(obj) {
  //   return Object.values(obj);
  // }
}
