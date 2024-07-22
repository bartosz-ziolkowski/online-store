import { Component } from '@angular/core';
import { MainContentComponent } from '../contact/main-content/main-content.component';

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [MainContentComponent],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.scss',
})
export class ContactComponent {}
