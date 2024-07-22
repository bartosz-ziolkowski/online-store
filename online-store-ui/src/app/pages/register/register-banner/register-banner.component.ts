import { Component } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@Component({
  selector: 'app-register-banner',
  standalone: true,
  imports: [FontAwesomeModule],
  templateUrl: './register-banner.component.html',
  styleUrl: './register-banner.component.scss',
})
export class RegisterBannerComponent {}
