import {
  Component,
  ElementRef,
  HostListener,
  OnInit,
  ViewChild,
  ViewEncapsulation,
} from '@angular/core';
import { IBrand, ICategory, IProduct } from '../../shared/model/product';
import { RouterModule, RouterOutlet } from '@angular/router';

import { CommonModule } from '@angular/common';
import { PageChangedEvent } from 'ngx-bootstrap/pagination';
import { PaginationComponent } from './pagination/pagination.component';
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
    PaginationComponent,
  ],
  templateUrl: './products.component.html',
  styleUrl: './products.component.scss',
  encapsulation: ViewEncapsulation.Emulated,
})
export class ProductsComponent implements OnInit {
  @ViewChild('search') searchTerm?: ElementRef;
  @ViewChild('menuContainer') menuContainer?: ElementRef;
  @ViewChild('menuButton') menuButton?: ElementRef;

  products: IProduct[] = [];
  categories: ICategory[] = [];
  brands: IBrand[] = [];
  productsParams: ProductsParams;
  totalCount = 0;
  menuOpen = false;

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

  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }

  @HostListener('document:click', ['$event'])
  onClick(event: Event) {
    if (
      this.menuButton &&
      this.menuContainer &&
      !this.menuButton.nativeElement.contains(event.target) &&
      !this.menuContainer.nativeElement.contains(event.target)
    ) {
      this.menuOpen = false;
    }
  }

  getProducts() {
    this.productsService.getProducts().subscribe({
      next: (res) => {
        if (res != null) this.products = res.dataList;
        this.productsParams.pageIndex = res.pageIndex;
        this.productsParams.pageSize = res.pageSize;
        this.totalCount = res.totalCount;
      },
    });
  }

  getCategories() {
    this.productsService.getCategories().subscribe({
      next: (res) => {
        this.categories = [{ categoryId: 0, categoryName: 'All' }, ...res];
      },
    });
  }

  getBrands() {
    this.productsService.getBrands().subscribe({
      next: (res) => {
        this.brands = [{ brandId: 0, brandName: 'All' }, ...res];
      },
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
