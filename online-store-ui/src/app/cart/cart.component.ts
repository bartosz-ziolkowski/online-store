import { CommonModule, Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ICart, ICartItem, ICartTotals } from '../shared/model/cart';

import { CartItemComponent } from './cart-item/cart-item.component';
import { CartService } from './cart.service';
import { IDeliveryMethod } from '../shared/model/delivery';
import { Observable } from 'rxjs';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule, CartItemComponent, RouterModule],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss',
})
export class CartComponent implements OnInit {
  cart$!: Observable<ICart | null>;
  cartTotals$!: Observable<ICartTotals | null>;
  deliveryMethods: IDeliveryMethod[] = [];

  constructor(public cartService: CartService, private location: Location) {}

  ngOnInit(): void {
    window.scrollTo({ top: 0, behavior: 'smooth' });
    this.cart$ = this.cartService.cart$;
    this.cartTotals$ = this.cartService.cartTotals$;
    this.cartService.getDeliveryMethods().subscribe((dm: IDeliveryMethod[]) => {
      this.deliveryMethods = dm;
    });
  }

  goBack() {
    this.location.back();
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  setShippingPrice(deliveryMethod: IDeliveryMethod) {
    this.cartService.setShippingPrice(deliveryMethod);
  }

  incrementItemQuantity(item: ICartItem) {
    this.cartService.incrementItemQuantity(item);
  }

  decrementItemQuantity(item: ICartItem) {
    this.cartService.decrementItemQuantity(item);
  }

  removeItemFromCart(item: ICartItem) {
    this.cartService.removeItemFromCart(item);
  }

  getTotalQuantity(cart: ICart): number {
    return cart.items.reduce((sum, item) => sum + item.quantity, 0);
  }
}
