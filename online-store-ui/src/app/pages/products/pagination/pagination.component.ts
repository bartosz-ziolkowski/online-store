import {
  Component,
  EventEmitter,
  Input,
  Output,
  ViewEncapsulation,
} from '@angular/core';
import { PageChangedEvent, PaginationModule } from 'ngx-bootstrap/pagination';

@Component({
  selector: 'app-pagination',
  standalone: true,
  imports: [PaginationModule],
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class PaginationComponent {
  @Input() boundaryLinks = true;
  @Input() directionLinks = true;
  @Input() rotate = true;
  @Input() totalItems = 0;
  @Input() itemsPerPage = 10;
  @Input() maxSize = 5;
  @Output() pageChanged = new EventEmitter<PageChangedEvent>();

  onPageChanged(event: PageChangedEvent) {
    this.pageChanged.emit(event);
  }
}
