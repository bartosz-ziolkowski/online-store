import { Component } from '@angular/core';

@Component({
  selector: 'app-main-content',
  standalone: true,
  templateUrl: './main-content.component.html',
  styleUrls: ['./main-content.component.scss'],
})
export class MainContentComponent {
  logoImage: string = '/assets/images/big_logo.png';
  storeImage: string = '/assets/images/store.png';
}
