import { Component } from '@angular/core';
import { HeroComponent } from './hero/hero.component';
import { NewsletterComponent } from './newsletter/newsletter.component';
import { ProductsCollectionComponent } from './products-collection/products-collection.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [HeroComponent, NewsletterComponent, ProductsCollectionComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {}
