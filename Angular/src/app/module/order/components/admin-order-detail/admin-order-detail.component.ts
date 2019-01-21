import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from '../../../product/service/product.service';
import { Product } from '../../../product/model/product.class';
import { OrderService } from '../../service/order.service';

@Component({
  selector: 'admin-order-detail',
  templateUrl: './admin-order-detail.component.html',
  styleUrls: ['./admin-order-detail.component.css']
})
export class AdminOrderDetailComponent implements OnInit {

  products: Product[] = []
  total: number = 0;

  @Input() orderID: number
  @Input() status: string
  @Input() customerID: number

  constructor(private router: Router, private productService: ProductService,
      private orderService: OrderService) { }

  ngOnInit() {
    if (localStorage.getItem('role') == 'customer') {
      this.router.navigate(['']);
    }
  }

  ngOnChanges() {
    this.getOrder(this.orderID);
  }

  /**
   * Get order
   * 
   * @since 15/12/2018
   */
  getOrder(orderID: number) {
    this.productService.getOrder(orderID, this.customerID).subscribe(order => {
      this.products = order;
      this.products.forEach((product) => {
        this.total += product.price * product.quantity;
      });
    });
  }

}