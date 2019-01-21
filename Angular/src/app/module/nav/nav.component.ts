import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/common/auth/auth.service';
import { CustomerService } from '../account/components/customer/service/customer.service';
import { User } from '../account/model/user.class';
import { OrderService } from '../order/service/order.service';
import { Order } from '../order/model/order.class';

@Component({
  selector: 'nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  isLoggedIn: boolean = false
  url: string
  avatar: string
  customer: User
  orders: Order[]

  constructor(private router: Router, public authService: AuthService,
      private customerService: CustomerService, private orderService: OrderService) { }

  ngOnInit() {
    if (localStorage.getItem('role') == 'customer') {
      this.isLoggedIn = true;
      this.avatar = localStorage.getItem('avatar');
      this.getOrderList();
    } else if (localStorage.getItem('role') == 'admin' || localStorage.getItem('role') == 'employee') {
      this.router.navigate(['/admin']);
    }

    this.url = this.router.url;
  }

  /**
   * Go to register page
   * 
   * @since 02/01/2019
   */
  goToRegister() {
    localStorage.setItem('url', this.router.url);
    this.router.navigate(['/register']);
  }

  /**
   * Go to login page
   * 
   * @since 02/01/2019
   */
  goToLogin() {
    if (this.router.url != '/register') {
      this.url = this.router.url;
    } else {
      this.url = '/';
    }

    localStorage.setItem('url', this.url);
    this.router.navigate(['/login']);
  }

  /**
   * Logout
   * 
   * @since 02/01/2019
   */
  logout() {
    this.authService.removeLocalStorage();
    location.reload();
  }

  /**
   * See personel information
   * 
   * @since 02/01/2019
   */
  seeInfo() {
    this.customerService.getByUserName(localStorage.getItem('token')).subscribe(customer => {
      localStorage.setItem('url', this.url);
      this.router.navigate(['/customers/', customer.id]);
    }, error => {
      console.log(error);
    });
  }

  /**
   * Get order list
   * 
   * @since 02/01/2019
   */
  getOrderList() {
    this.customerService.getByUserName(localStorage.getItem('token')).subscribe(customer => {
      this.customer = customer;
      this.orderService.getList(this.customer.id).subscribe(orders => {
        this.orders = orders;
      }, error => {
        console.log(error);
      });
    }, error => {
      console.log(error);
    });
  }

  /**
   * See order detail
   * 
   * @since 27/12/2018
   * @param id
   */
  seeDetail(orderID: number, customerID: number) {
    this.router.navigate(['/order/', orderID, customerID]);
  }

}