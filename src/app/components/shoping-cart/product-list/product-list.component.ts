import { Component, OnInit } from '@angular/core';
import {ProductModel} from 'src/app/components/models/product-model';
import {ProductService} from 'src/app/services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {
  productList: ProductModel[];

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    console.log(this.productService.list().subscribe((data) => this.productList = data));
  }

}
