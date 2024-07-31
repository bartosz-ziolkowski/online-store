import {
  Component,
  ElementRef,
  OnInit,
  ViewChild,
  ViewEncapsulation,
} from '@angular/core';
import { IBrand, ICategory, IProduct } from '../../shared/model/product';
import { PageChangedEvent, PaginationModule } from 'ngx-bootstrap/pagination';
import { RouterModule, RouterOutlet } from '@angular/router';

import { CommonModule } from '@angular/common';
import { ProductItemComponent } from './product-item/product-item.component';
import { ProductsParams } from '../../shared/model/productsParams';
import { ProductsService } from './products.service';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    RouterModule,
    ProductItemComponent,
    PaginationModule,
  ],
  templateUrl: './products.component.html',
  styleUrl: './products.component.scss',
  encapsulation: ViewEncapsulation.None,
})
export class ProductsComponent implements OnInit {
  @ViewChild('search') searchTerm?: ElementRef;
  products: IProduct[] = [];
  categories: ICategory[] = [];
  brands: IBrand[] = [];
  productsParams: ProductsParams;
  totalCount = 0;
  sortOptions = [
    { name: 'Alphabetical', value: 'title' },
    { name: 'Price: Low to high', value: 'priceAsc' },
    { name: 'Price: High to low', value: 'priceDesc' },
  ];

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
        this.productsParams.pageIndex = res.pageIndex;
        this.productsParams.pageSize = res.pageSize;
        this.totalCount = res.totalCount;
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

  onSortSelected(event: any) {
    const params = this.productsService.getProductsParams();
    params.sort = event.target.value;
    this.productsService.setProductsParams(params);
    this.productsParams = params;
    this.getProducts();
  }

  onPageChanged(event: PageChangedEvent) {
    const params = this.productsService.getProductsParams();
    if (params.pageIndex !== event.page) {
      params.pageIndex = event.page;

      this.productsService.setProductsParams(params);
      this.productsParams = params;
      this.getProducts();
    }
  }

  onSearch() {
    const params = this.productsService.getProductsParams();
    params.search = this.searchTerm?.nativeElement.value;
    params.pageIndex = 1;
    this.productsService.setProductsParams(params);
    this.productsParams = params;
    this.getProducts();
  }

  onReset() {
    if (this.searchTerm) this.searchTerm.nativeElement.value = '';
    this.productsParams = new ProductsParams();
    this.productsService.setProductsParams(this.productsParams);
    this.getProducts();
  }
}
