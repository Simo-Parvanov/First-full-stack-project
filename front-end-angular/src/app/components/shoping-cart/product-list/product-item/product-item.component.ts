import {Component, Input, OnInit} from '@angular/core';
import {ProductModel} from 'src/app/components/models/product-model';
import { MessengerService } from 'src/app/services/messenger.service';
import {CartService} from 'src/app/services/cart.service';
import {ActivatedRoute, Route, Router} from '@angular/router';

@Component({
  selector: 'app-product-item',
  templateUrl: './product-item.component.html',
  styleUrls: ['./product-item.component.css']
})
export class ProductItemComponent implements OnInit {
  @Input() productItem: ProductModel;

  constructor(private msg: MessengerService, private cartService: CartService,  private router: Router) { }

  ngOnInit(): void {
  }

  handleAddToCart(){
    this.cartService.addProductToCart(this.productItem).subscribe(()=>{
      this.msg.sendMsg(this.productItem)
    })
  }
  productDetail(){
    this.cartService.addProductToCart(this.productItem).subscribe(()=> {
      this.msg.sendMsg(this.productItem)
      console.log(this.productItem)
    })
    this.router.navigate(['product/'+ this.productItem.id])
  }

}
