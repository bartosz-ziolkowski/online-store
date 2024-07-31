import { ActivatedRoute, RouterModule } from '@angular/router';
import { Component, OnInit } from '@angular/core';

import { CartService } from '../../cart/cart.service';
import { CommonModule } from '@angular/common';
import { IProduct } from '../../shared/model/product';
import { Location } from '@angular/common';
import { ProductService } from './product.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './product.component.html',
  styleUrl: './product.component.scss',
})
export class ProductComponent implements OnInit {
  product?: IProduct;
  productId: number = -1;

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private location: Location,
    private cartService: CartService,
    private toasterService: ToastrService
  ) {}

  ngOnInit(): void {
    window.scrollTo({ top: 0, behavior: 'smooth' });
    this.route.params.subscribe((params) => {
      this.productId = params['id'];
    });
    this.getProduct(this.productId);
  }

  goBack() {
    this.location.back();
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }

  getProduct(productId: number) {
    this.productService.getProduct(productId).subscribe({
      next: (res) => {
        if (res != null) this.product = res;
      },
    });
  }

  addItemToCart(e: Event) {
    e.preventDefault();
    this.product && this.cartService.addItemToCart(this.product);
    this.toasterService.success('Item added');
  }
}
