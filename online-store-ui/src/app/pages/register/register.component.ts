import { Component } from '@angular/core';
import { RegisterBannerComponent } from './register-banner/register-banner.component';
import { RegisterFormComponent } from './register-form/register-form.component';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RegisterBannerComponent, RegisterFormComponent],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
})
export class RegisterComponent {}
