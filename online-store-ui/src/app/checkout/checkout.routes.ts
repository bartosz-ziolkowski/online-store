import { AuthGuard } from '../auth/auth.guard';
import { CheckoutComponent } from './checkout.component';
import { Routes } from '@angular/router';

export const CHECKOUT_ROUTES: Routes = [
  {
    path: '',
    component: CheckoutComponent,
    canActivate: [AuthGuard],
    data: { roles: ['USER'] },
  },
];
