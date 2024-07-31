import { AsyncPipe, CommonModule, CurrencyPipe } from '@angular/common';
import { BehaviorSubject, Observable } from 'rxjs';
import { Component, OnInit, ViewChild, signal } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ICart, ICartTotals } from '../shared/model/cart';
import {
  NgxStripeModule,
  StripeFactoryService,
  StripePaymentElementComponent,
  StripeServiceInterface,
} from 'ngx-stripe';
import { Router, RouterModule } from '@angular/router';
import {
  StripeElementsOptions,
  StripePaymentElementOptions,
} from '@stripe/stripe-js';

import { CartService } from '../cart/cart.service';
import { CheckoutItemComponent } from './checkout-item/checkout-item.component';
import { ToastrService } from 'ngx-toastr';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-checkout',
  standalone: true,
  imports: [
    RouterModule,
    CurrencyPipe,
    NgxStripeModule,
    CommonModule,
    AsyncPipe,
    CheckoutItemComponent,
    StripePaymentElementComponent,
    ReactiveFormsModule,
  ],
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss'],
})
export class CheckoutComponent implements OnInit {
  stripe: StripeServiceInterface;
  cartTotals$!: Observable<ICartTotals | null>;
  cart$: Observable<ICart | null>;

  loading = new BehaviorSubject<boolean>(false);

  @ViewChild(StripePaymentElementComponent)
  paymentElement!: StripePaymentElementComponent;

  constructor(
    private fb: FormBuilder,
    private cartService: CartService,
    private toasterService: ToastrService,
    private stripeFactory: StripeFactoryService,
    private router: Router
  ) {
    this.cart$ = this.cartService.cart$;
    this.stripe = this.stripeFactory.create(environment.stripe.publicKey);
  }

  paymentElementForm = this.fb.group({
    name: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    address: ['', [Validators.required]],
    zipcode: ['', [Validators.required]],
    city: ['', [Validators.required]],
    state: ['', [Validators.required]],
  });

  elementsOptions: StripeElementsOptions = {
    locale: 'en',
    clientSecret: '',
    appearance: {
      theme: 'flat',
    },
  };

  paymentElementOptions: StripePaymentElementOptions = {
    layout: {
      type: 'tabs',
      defaultCollapsed: false,
      radios: false,
      spacedAccordionItems: false,
    },
  };

  paying = signal(false);

  ngOnInit() {
    window.scrollTo({ top: 0, behavior: 'smooth' });
    this.cartTotals$ = this.cartService.cartTotals$;
  }

  proceedToPayment(e: Event) {
    e.preventDefault();
    if (this.paying() || this.paymentElementForm.invalid) {
      this.toasterService.error('Correct your data');
      return;
    } else {
      this.loading.next(true);
      this.cartService.createPaymentIntent().subscribe({
        next: () => {
          const cart = this.cartService.getCurrentCart();
          if (cart) {
            this.elementsOptions = {
              locale: 'en',
              clientSecret: cart.clientSecret,
              appearance: {
                theme: 'flat',
              },
            };
            this.paying.set(true);
            this.loading.next(false);
          }
        },
        error: (error: string | undefined) => {
          this.toasterService.error('Try again');
          this.loading.next(false);
        },
      });
    }
  }

  pay() {
    const { name, email, address, zipcode, city, state } =
      this.paymentElementForm.getRawValue();
    this.loading.next(true);
    this.stripe
      .confirmPayment({
        elements: this.paymentElement.elements,
        confirmParams: {
          payment_method_data: {
            billing_details: {
              name: name as string,
              email: email as string,
              address: {
                line1: address as string,
                postal_code: zipcode as string,
                city: city as string,
                state: state as string,
              },
            },
          },
        },
        redirect: 'if_required',
      })
      .subscribe((result) => {
        this.paying.set(false);
        if (result.error) {
          return;
        } else {
          if (result.paymentIntent.status === 'succeeded') {
            this.deleteCart();
          }
        }
        this.loading.next(false);
      });
  }

  deleteCart() {
    const cart = this.cartService.getCurrentCart();
    if (cart) {
      this.cartService.deleteCart(cart!);
      this.router.navigate(['/']);
      window.scrollTo({ top: 0, behavior: 'smooth' });
      this.toasterService.success('Payment succeeded');
    }
  }

  goBack() {
    window.history.back();
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }
}
