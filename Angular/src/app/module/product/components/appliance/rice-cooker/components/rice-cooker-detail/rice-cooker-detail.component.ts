import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { RiceCookerService } from '../../service/rice.cooker.service';
import { OrderService } from 'src/app/module/order/service/order.service';
import { CustomerService } from 'src/app/module/account/components/customer/service/customer.service';
import { RiceCooker } from '../../model/rice.cooker.class';
import { Order } from 'src/app/module/order/model/order.class';
import { User } from 'src/app/module/account/model/user.class';
import swal from 'sweetalert2';

@Component({
  selector: 'rice-cooker-detail',
  templateUrl: './rice-cooker-detail.component.html',
  styleUrls: ['./rice-cooker-detail.component.css']
})
export class RiceCookerDetailComponent implements OnInit {

  riceCooker: RiceCooker = new RiceCooker()
  order: Order = new Order()
  customer: User = new User()
  quantitySelected = 1;

  constructor(private activateRoute: ActivatedRoute, private router: Router,
      private riceCookerService: RiceCookerService, private customerService: CustomerService,
      private orderService: OrderService) { }

  ngOnInit() {
    const name = this.activateRoute.snapshot.params['name'];
    this.riceCookerService.getByName(name).subscribe(riceCooker => {
      this.riceCooker = riceCooker;
    });
  }

  /**
   * Check quantity is validate
   * 
   * @since 15/12/2018
   */
  checkQuantity() {
    if (this.quantitySelected > this.riceCooker.quantity) {
      this.quantitySelected = this.riceCooker.quantity - 1;
    } else if (this.quantitySelected < 0) {
      if (this.riceCooker.quantity <= 0) {
        this.quantitySelected = 0;
      } else {
        this.quantitySelected = 1;
      }
    }

    if (typeof this.quantitySelected == 'string') {
      if (this.riceCooker.quantity > 0) {
        if (this.riceCooker.quantity >= 1) {
          this.quantitySelected = 1;
        } else {
          this.quantitySelected = 0;
        }
      } else {
        this.quantitySelected = 0;
      }
    }
  }

  /**
   * Add 1 to a quantity
   * 
   * @since 15/12/2018
   */
  add() {
    if (this.quantitySelected < this.riceCooker.quantity - 1) {
      this.quantitySelected++;
    }
  }

  /**
   * Subtract 1 to a quantity
   * 
   * @since 15/12/2018
   */
  subtract() {
    if (this.quantitySelected < 2) {
      return;
    }

    this.quantitySelected--;
  }

  /**
   * Check if user logged in
   * 
   * @since 02/01/2019
   */
  checkLoggedIn() {
    if (localStorage.getItem('role') == null) {
      localStorage.setItem('url', this.router.url);
      this.router.navigate(['/login']);
    }
  }

  /**
   * Add to cart
   * 
   * @since 02/01/2019
   */
  addToCart() {
    this.checkLoggedIn();
    this.customerService.getByUserName(localStorage.getItem('token')).subscribe(customer => {
      this.customer = customer;
      this.order.customerID = customer.id;
      this.order.productID = this.riceCooker.id;
      this.order.quantity = this.quantitySelected;

      // Check if customer has had cart
      this.orderService.getByCustomerID(customer.id).subscribe(order => {
        if (order == null) {
          this.order.dateCreated = new Date;
          this.orderService.add(this.order).subscribe(data => {
            this.riceCooker.quantity -= this.quantitySelected;
            swal({
              type: 'success',
              title: 'Đã thêm sản phẩm ' + this.riceCooker.name + ' thành công!'
            });
            this.quantitySelected = 1;
          }, error => {
            swal({
              type: 'error',
              title: 'Thêm sản phẩm ' + this.riceCooker.name + ' thất bại!'
            });

            console.log(error);
          });
        } else {
          this.order.id = order.id;
          this.orderService.addNewProductToOrder(this.order).subscribe(data => {
            this.riceCooker.quantity -= this.quantitySelected;
            swal({
              type: 'success',
              title: 'Đã thêm sản phẩm ' + this.riceCooker.name + ' thành công!'
            });
            this.quantitySelected = 1;
          }, error => {
            swal({
              type: 'error',
              title: 'Thêm sản phẩm ' + this.riceCooker.name + ' thất bại!'
            });
          });
        }
      }, error => {
        swal({
          type: 'error',
          title: 'Thêm sản phẩm ' + this.riceCooker.name + ' thất bại!'
        });

        console.log(error);
      });
    });
  }

  /**
   * Buy product
   * 
   * @since 02/01/2019
   */
  buy() {
    this.addToCart();
    this.router.navigate(['/order/', this.order.id]);
  }

}