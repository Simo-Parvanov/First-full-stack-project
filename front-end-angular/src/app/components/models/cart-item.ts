import {ProductModel} from './product-model';

export class CartItem {
  id: string;
  productId: string;
  name: string;
  quantity: number;
  price: number;
  priceOld: number;
  discount: number;
  image: [];

  constructor(id: string, product: ProductModel, qty = 1) {
    this.id = id;
    this.productId = product.id;
    this.name = product.name;
    this.price = product.price;
    this.quantity = qty;
    this.image = product.image;
  }
}

