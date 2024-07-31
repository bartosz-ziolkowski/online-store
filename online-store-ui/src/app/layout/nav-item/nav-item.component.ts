import { Component, EventEmitter, Input, Output, ViewEncapsulation } from '@angular/core';

import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-nav-item',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './nav-item.component.html',
  styleUrl: './nav-item.component.scss',
  encapsulation: ViewEncapsulation.None,
})
export class NavItemComponent {
  @Input() NavLink!: string;
  @Output() closeMenu = new EventEmitter<void>();

  close() {
    this.closeMenu.emit();
  }
}
