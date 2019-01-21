import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OrderService } from '../../service/order.service';
import { CustomerService } from 'src/app/module/account/components/customer/service/customer.service';
import { Order } from '../../model/order.class';

@Component({
  selector: 'order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {

  totalItems: number
  currentPage = 1
  pagedItems: Order[]
  pager: any = {}
  isConnected = false
  orderID: number
  status = ''
  customerID: number

  constructor(private router: Router, private orderService: OrderService,
    private customerService: CustomerService) { }

  ngOnInit() {
    if (localStorage.getItem('role') == 'customer') {
      this.router.navigate(['']);
    }

    this.countAll();
  }

  /**
   * Count list of orders quantity
   * 
   * @since 27/12/2018
   */
  countAll() {
    this.orderService.countAll().subscribe(quantity => {
      this.totalItems = quantity;
      this.isConnected = true;
    }, error => {
      console.log(error);
    });
  }

  /**
   * Page changed
   * 
   * @since 27/12/2018
   * @param event
   */
  pageChanged(event) {
    this.currentPage = event.currentPage;
    this.getPerPage(this.currentPage - 1);
  }

  /**
   * Get list of orders per page
   * 
   * @since 15/12/2018
   * @param page
   */
  getPerPage(page: number) {
    this.orderService.getPerPage(page).subscribe(orders => {
      this.pagedItems = orders;
    }, error => {
      console.log(error);
    });
  }

  /**
   * See order detail
   * 
   * @since 02/01/2019
   * @param orderID
   * @param status
   */
  seeDetail(orderID: number, status: string, customerID: number){
    this.orderID = orderID;
    this.status = status;
    this.customerID = customerID;
  }

}