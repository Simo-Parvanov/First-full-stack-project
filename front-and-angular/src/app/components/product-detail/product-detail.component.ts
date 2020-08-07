import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
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
    this.loadProductDetails(this.productID);
  }

  loadProductDetails(productID){
    this.productService.getProductDetails(productID).subscribe(data => {
      this.productData = data;
    })
  }
}
