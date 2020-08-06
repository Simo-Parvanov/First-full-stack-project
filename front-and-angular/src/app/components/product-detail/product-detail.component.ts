import {Component, Input, OnInit} from '@angular/core';
import {ProductModel} from 'src/app/components/models/product-model';
import {ActivatedRoute, Router, UrlMatchResult} from '@angular/router';
import {ProductItemComponent} from '../shoping-cart/product-list/product-item/product-item.component';
import {ProductService} from 'src/app/services/product.service';

@Component({
  selector: 'app-product-redail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  productID: any;
  productData: any;
  constructor(private actRouter: ActivatedRoute,
              private router: Router,
              private productService: ProductService) { }

  ngOnInit(): void {
    this.productID = this.actRouter.snapshot.params.id;
    console.log(this.productID)
    console.log(this.productData)
    console.log(this.productData)
    console.log(this.productData)
    this.loadProductDetails();
  }

  loadProductDetails(){
    this.productService.getProductDetails(this.productID).subscribe(data => {
      this.productData = data;
    })
  }
}
