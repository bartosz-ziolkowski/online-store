import { AuthGuard } from '../../auth/auth.guard';
import { ProductsComponent } from './products.component';
import { Routes } from '@angular/router';

export const PRODUCTS_ROUTES: Routes = [
  {
    path: '',
    component: ProductsComponent,
  },
];
