import {Component, Input, OnInit} from '@angular/core';
import {ProductModel} from 'src/app/components/models/product-model';

@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.css']
})
export class ProductItemComponent implements OnInit {
  @Input() productItem: ProductModel[];

  constructor() { }

  ngOnInit(): void {
  }

  getKeys(obj) {
    return Object.values(obj);
  }
}
