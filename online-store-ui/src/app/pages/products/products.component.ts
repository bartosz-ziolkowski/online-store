import { Component, OnInit } from '@angular/core';
import { IBrand, ICategory, IProduct } from '../../shared/model/product';

import { CommonModule } from '@angular/common';
import { ProductItemComponent } from './product-item/product-item.component';
import { ProductsParams } from '../../shared/model/productsParams';
import { ProductsService } from './products.service';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [CommonModule, ProductItemComponent],
  templateUrl: './products.component.html',
  styleUrl: './products.component.scss',
})
export class ProductsComponent implements OnInit {
  products: IProduct[] = [];
  categories: ICategory[] = [];
  brands: IBrand[] = [];
  productsParams: ProductsParams;

  constructor(private productsService: ProductsService) {
    this.productsParams = this.productsService.getProductsParams();
  }

  ngOnInit(): void {
    this.getProducts();
    this.getCategories();
    this.getBrands();
  }

  getProducts() {
    this.productsService.getProducts().subscribe({
      next: (res) => {
        if (res != null) this.products = res.dataList;
      },
      error: (err) => console.log(err),
    });
  }

  getCategories() {
    this.productsService.getCategories().subscribe({
      next: (res) => {
        this.categories = [{ categoryId: 0, categoryName: 'All' }, ...res];
      },
      error: (err) => console.log(err),
    });
  }

  getBrands() {
    this.productsService.getBrands().subscribe({
      next: (res) => {
        this.brands = [{ brandId: 0, brandName: 'All' }, ...res];
      },
      error: (err) => console.log(err),
    });
  }

  onBrandSelected(brandId: number) {
    const params = this.productsService.getProductsParams();
    params.brandId = brandId;
    this.productsService.setProductsParams(params);
    this.productsParams = params;
    this.getProducts();
  }

  onCategorySelected(categoryId: number) {
    const params = this.productsService.getProductsParams();
    params.categoryId = categoryId;
    this.productsService.setProductsParams(params);
    this.productsParams = params;
    this.getProducts();
  }
}
