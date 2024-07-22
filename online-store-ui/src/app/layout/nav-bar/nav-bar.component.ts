import { CommonModule, NgOptimizedImage } from '@angular/common';

import { Component } from '@angular/core';
import { NavItemComponent } from '../nav-item/nav-item.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [NavItemComponent, CommonModule, NgOptimizedImage, RouterModule],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.scss',
})
export class NavBarComponent {
  open = false;

  toggleNavbar() {
    this.open = !this.open;
  }

  closeMenu() {
    this.open = false;
  }
}
