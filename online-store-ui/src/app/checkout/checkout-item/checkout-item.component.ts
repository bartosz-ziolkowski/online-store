import { AsyncPipe, CommonModule, CurrencyPipe } from '@angular/common';
import { Component, Input } from '@angular/core';

import { ICartItem } from '../../shared/model/cart';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-checkout-item',
  standalone: true,
  imports: [CurrencyPipe, CommonModule, AsyncPipe, RouterModule],
  templateUrl: './checkout-item.component.html',
  styleUrl: './checkout-item.component.scss',
})
export class CheckoutItemComponent {
  @Input() item!: ICartItem;
}
