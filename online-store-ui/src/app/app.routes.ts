import { AboutComponent } from './pages/about/about.component';
import { ContactComponent } from './pages/contact/contact.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { ProductComponent } from './pages/product/product.component';
import { ProductsComponent } from './pages/products/products.component';
import { RegisterComponent } from './pages/register/register.component';
import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
  },
  {
    path: 'about',
    component: AboutComponent,
  },
  {
    path: 'contact',
    component: ContactComponent,
  },
  {
    path: 'products/:id',
    component: ProductComponent,
  },
  {
    path: 'products',
    loadChildren: () =>
      import('../app/pages/products/products.routes').then(
        (r) => r.PRODUCTS_ROUTES
      ),
  },
  {
    path: 'cart',
    loadChildren: () =>
      import('../app/cart/cart.routes').then((r) => r.CART_ROUTES),
  },
  {
    path: 'checkout',
    loadChildren: () =>
      import('../app/checkout/checkout.routes').then((r) => r.CHECKOUT_ROUTES),
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'register',
    component: RegisterComponent,
  },
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full',
  },
  {
    path: '**',
    redirectTo: '/home',
    pathMatch: 'full',
  },
];
