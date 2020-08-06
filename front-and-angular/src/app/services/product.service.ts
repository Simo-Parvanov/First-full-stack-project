import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import { ProductModel } from 'src/app/components/models/product-model';
import {map, tap} from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class ProductService {

  imageUrl = 'http://localhost:8080/product/';
  cartUrl = 'http://localhost:8080/cart/';

  constructor(private httpClient: HttpClient) { }

  public list(): Observable<ProductModel[]> {
    return this.httpClient.get<ProductModel[]>(this.imageUrl + 'all').pipe(tap(console.log));
  }

  public getProductDetails(id){
    return this.httpClient.get(this.imageUrl + 'detail/' + id)
  }
}

