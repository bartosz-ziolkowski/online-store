import { HttpClient, HttpParams } from '@angular/common/http';
import { IBrand, ICategory, IProduct } from '../../shared/model/product';
import { Observable, map } from 'rxjs';

import { IPagination } from '../../shared/model/pagination';
import { Injectable } from '@angular/core';
import { ProductsParams } from './../../shared/model/productsParams';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ProductsService {
  productsParams = new ProductsParams();
  pagination?: IPagination<IProduct[]>;

  constructor(private http: HttpClient) {}

  getProducts(): Observable<IPagination<IProduct[]>> {
    let params = new HttpParams();
    if (this.productsParams.brandId > 0)
      params = params.append('brandId', this.productsParams.brandId);

    if (this.productsParams.categoryId > 0)
      params = params.append('categoryId', this.productsParams.categoryId);

    return this.http
      .get<IPagination<IProduct[]>>(environment.API_URL + 'store/products', {
        params: params,
      })
      .pipe(
        map((res) => {
          this.pagination = res;
          return res;
        })
      );
  }

  getBrands() {
    return this.http.get<IBrand[]>(environment.API_URL + 'store/brands');
  }

  getCategories() {
    return this.http.get<ICategory[]>(environment.API_URL + 'store/categories');
  }

  setProductsParams(params: ProductsParams) {
    this.productsParams = params;
  }

  getProductsParams() {
    return this.productsParams;
  }
}
