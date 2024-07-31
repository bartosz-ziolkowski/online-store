import { Component, Input } from '@angular/core';

import { CartService } from '../../../cart/cart.service';
import { CommonModule } from '@angular/common';
import { IProduct } from '../../../shared/model/product';
import { RouterModule } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-product-item',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './product-item.component.html',
  styleUrl: './product-item.component.scss',
})
export class ProductItemComponent {
  @Input() product?: IProduct;

  constructor(
    private cartService: CartService,
    private toasterService: ToastrService
  ) {}

  addItemToCart(e: Event) {
    e.preventDefault();
    this.product && this.cartService.addItemToCart(this.product);
    this.toasterService.success('Item added');
  }
}
