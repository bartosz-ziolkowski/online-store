import { environment } from '../../../environments/environment';

export class ProductsParams {
  brandId = 0;
  categoryId = 0;
  pageIndex = 1;
  pageSize = environment.pageSize;
  sort = 'name';
  search = '';
}
