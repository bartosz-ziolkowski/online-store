import { Component, EventEmitter, Input, Output } from '@angular/core';

import { CommonModule } from '@angular/common';
import { ICartItem } from '../../shared/model/cart';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-cart-item',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './cart-item.component.html',
  styleUrl: './cart-item.component.scss',
})
export class CartItemComponent {
  @Input() item!: ICartItem;
  @Output() increment: EventEmitter<ICartItem> = new EventEmitter<ICartItem>();
  @Output() decrement: EventEmitter<ICartItem> = new EventEmitter<ICartItem>();
  @Output() removeItem: EventEmitter<ICartItem> = new EventEmitter<ICartItem>();

  incrementItemQuantity(item: ICartItem) {
    this.increment.emit(item);
  }

  decrementItemQuantity(item: ICartItem) {
    this.decrement.emit(item);
  }

  removeCartItem(item: ICartItem) {
    this.removeItem.emit(item);
  }
}
