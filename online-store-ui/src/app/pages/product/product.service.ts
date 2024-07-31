import { HttpClient } from '@angular/common/http';
import { IProduct } from '../../shared/model/product';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private http: HttpClient) {}

  getProduct(productId: number) {
    return this.http.get<IProduct>(
      environment.API_URL + 'store/products/' + productId
    );
  }
}
