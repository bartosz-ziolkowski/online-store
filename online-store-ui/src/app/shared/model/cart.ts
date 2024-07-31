import { createId } from '@paralleldrive/cuid2';

export interface ICart {
  id: string;
  items: ICartItem[];
  shippingPrice: number;
  deliveryMethodId?: number;
  clientSecret?: string;
  paymentIntentId?: string;
}

export interface ICartItem {
  productId: number;
  title: string;
  unitPrice: number;
  quantity: number;
  imageUrl: string;
  brandName: string;
  categoryName: string;
}

export class Cart implements ICart {
  shippingPrice: number = 0;
  id = createId();
  items: ICartItem[] = [];
  deliveryMethodId?: number;
}

export interface ICartTotals {
  subtotal: number;
  shipping: number;
  total: number;
}
