import { Component, OnInit } from '@angular/core';

import { CommonModule } from '@angular/common';
import { IProduct } from '../../../shared/model/product';
import { ProductsService } from '../../products/products.service';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-products-collection',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './products-collection.component.html',
  styleUrl: './products-collection.component.scss',
})
export class ProductsCollectionComponent implements OnInit {
  products: IProduct[] = [];

  constructor(private productsService: ProductsService) {}

  ngOnInit(): void {
    this.productsService.getProducts().subscribe({
      next: (res) => {
        this.products = this.getRandomShoes(res.dataList, 4);
      },
    });
  }

  getRandomShoes(shoes: IProduct[], count: number): IProduct[] {
    if (shoes.length <= count) {
      return shoes;
    }
    const shuffled = shoes.sort(() => 0.5 - Math.random());
    return shuffled.slice(0, count);
  }
}
