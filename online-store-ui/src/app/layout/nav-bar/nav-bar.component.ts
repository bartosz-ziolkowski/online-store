import { AsyncPipe, CommonModule, NgOptimizedImage } from '@angular/common';
import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterModule } from '@angular/router';

import { CartService } from '../../cart/cart.service';
import { ICart } from '../../shared/model/cart';
import { KeycloakProfile } from 'keycloak-js';
import { KeycloakService } from 'keycloak-angular';
import { NavItemComponent } from '../nav-item/nav-item.component';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [
    NavItemComponent,
    CommonModule,
    NgOptimizedImage,
    RouterModule,
    RouterLinkActive,
    AsyncPipe,
  ],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.scss',
  encapsulation: ViewEncapsulation.None,
})
export class NavBarComponent implements OnInit {
  open = false;
  cart$: Observable<ICart | null>;
  public loggedIn = false;
  public userProfile: KeycloakProfile | null = null;

  constructor(
    private cartService: CartService,
    private keycloak: KeycloakService
  ) {
    this.cart$ = this.cartService.cart$;
  }

  ngOnInit(): void {
    this.isLogged();
  }

  async isLogged() {
    this.loggedIn = await this.keycloak.isLoggedIn();
    if (this.loggedIn) {
      this.userProfile = await this.keycloak.loadUserProfile();
    }
  }

  public login() {
    this.keycloak.login();
  }

  public logout() {
    this.keycloak.logout();
  }

  getUsername() {
    return this.userProfile?.username;
  }

  getEmail() {
    return this.userProfile?.email;
  }

  toggleNavbar() {
    this.open = !this.open;
  }

  closeMenu() {
    this.open = false;
  }
}
