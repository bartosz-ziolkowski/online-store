import { BehaviorSubject, map } from 'rxjs';
import { Cart, ICart, ICartItem, ICartTotals } from '../shared/model/cart';
import { Injectable, OnInit } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { IDeliveryMethod } from '../shared/model/delivery';
import { IProduct } from '../shared/model/product';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private cartSource = new BehaviorSubject<ICart | null>(null);
  cart$ = this.cartSource.asObservable();
  private cartTotalsSource = new BehaviorSubject<ICartTotals | null>(null);
  cartTotals$ = this.cartTotalsSource.asObservable();
  basketUrl = environment.API_BASKET_URL;
  baseUrl = environment.API_URL;

  constructor(private http: HttpClient) {}

  createCart(): Cart {
    const cart = new Cart();
    localStorage.setItem('angular_cart_id', cart.id);
    return cart;
  }

  getCurrentCart() {
    return this.cartSource.value;
  }

  private calculateTotals() {
    const cart = this.getCurrentCart();
    if (!cart) return;
    const shipping = cart.shippingPrice;
    const subtotal = cart.items.reduce(
      (a, b) => b.unitPrice * b.quantity + a,
      0
    );
    const total = subtotal + shipping;
    this.cartTotalsSource.next({ shipping: shipping, total, subtotal });
  }

  getCart(id: string) {
    return this.http.get<ICart>(this.basketUrl + '/' + id).pipe(
      map((cart: ICart) => {
        this.cartSource.next(cart);
        this.calculateTotals();
      })
    );
  }

  setCart(cart: ICart) {
    return this.http
      .post<ICart>(this.basketUrl, cart)
      .subscribe((res: ICart) => {
        this.cartSource.next(res);
        this.calculateTotals();
      });
  }

  addItemToCart(product: IProduct, quantity = 1) {
    const itemToAdd = this.mapProductToCartItem(product);
    const cart = this.getCurrentCart() ?? this.createCart();
    cart.items = this.addOrUpdateItem(cart.items, itemToAdd, quantity);
    this.setCart(cart);
  }

  createPaymentIntent() {
    return this.http
      .post<ICart>(this.baseUrl + 'payments/' + this.getCurrentCart()?.id, {})
      .pipe(
        map((cart) => {
          this.cartSource.next(cart);
        })
      );
  }

  addOrUpdateItem(
    items: ICartItem[],
    itemToAdd: ICartItem,
    quantity: number
  ): any {
    const itemFound = items.find((i) => i.productId == itemToAdd.productId);
    if (itemFound) {
      itemFound.quantity += quantity;
    } else {
      itemToAdd.quantity = quantity;
      items.push(itemToAdd);
    }
    return items;
  }

  mapProductToCartItem(product: IProduct): ICartItem {
    return {
      productId: product.productId,
      title: product.title,
      unitPrice: product.unitPrice,
      quantity: 1,
      imageUrl: product.imageUrl,
      brandName: product.brandName,
      categoryName: product.categoryName,
    };
  }

  incrementItemQuantity(item: ICartItem) {
    const cart = this.getCurrentCart();
    if (cart) {
      const foundItemIndex = cart.items.findIndex(
        (i) => i.productId == item.productId
      );
      cart.items[foundItemIndex].quantity++;
      this.setCart(cart);
    }
  }

  decrementItemQuantity(item: ICartItem) {
    const cart = this.getCurrentCart();
    if (cart) {
      const foundItemIndex = cart.items.findIndex(
        (i) => i.productId == item.productId
      );
      if (cart.items[foundItemIndex].quantity > 1) {
        cart.items[foundItemIndex].quantity--;
        this.setCart(cart);
      } else {
        this.removeItemFromCart(item);
      }
    }
  }

  removeItemFromCart(item: ICartItem) {
    const cart = this.getCurrentCart();
    if (cart && cart.items.some((i) => i.productId == item.productId)) {
      cart.items = cart.items.filter((i) => i.productId != item.productId);
      if (cart.items.length > 0) {
        this.setCart(cart);
      } else {
        this.deleteCart(cart);
      }
    }
  }

  deleteCart(cart: ICart) {
    return this.http
      .delete(this.basketUrl + '/' + cart.id, { responseType: 'text' })
      .subscribe({
        next: () => {
          this.cartSource.next(null);
          this.cartTotalsSource.next(null);
          localStorage.removeItem('angular_cart_id');
        },
      });
  }

  setShippingPrice(deliveryMethod: IDeliveryMethod) {
    const cart = this.getCurrentCart();
    if (cart) {
      cart.deliveryMethodId = deliveryMethod.deliveryMethodId;
      cart.shippingPrice = deliveryMethod.price;
      this.setCart(cart);
    }
  }

  getDeliveryMethods() {
    return this.http
      .get<IDeliveryMethod[]>(this.baseUrl + 'deliverymethods')
      .pipe(
        map((dm) => {
          return dm.sort((a, b) => a.price - b.price);
        })
      );
  }
}
