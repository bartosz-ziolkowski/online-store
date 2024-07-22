import { Component } from '@angular/core';
import { MainContentComponent } from './main-content/main-content/main-content.component';

@Component({
  selector: 'app-about',
  standalone: true,
  imports: [MainContentComponent],
  templateUrl: './about.component.html',
  styleUrl: './about.component.scss',
})
export class AboutComponent {}
