import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../../product/service/product.service';
import { Product } from '../../../product/model/product.class';
import { ActivatedRoute, Router, ParamMap } from '@angular/router';
import { OrderService } from '../../service/order.service';
import { Order } from '../../model/order.class';
import swal from 'sweetalert2';

@Component({
  selector: 'order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.css']
})
export class OrderDetailComponent implements OnInit {

  products: Product[] = []
  order: Order = new Order
  orderID: number
  customerID: number
  title: string = 'Hoá đơn'
  total: number = 0;
  orderTitle: string = ''

  constructor(private activateRoute: ActivatedRoute, private router: Router,
    private productService: ProductService, private orderService: OrderService) { }

  ngOnInit() {
    if (localStorage.getItem('role') != 'customer') {
      this.router.navigate(['/admin']);
    }

    this.activateRoute.paramMap.subscribe((param: ParamMap) => {
      this.orderID = parseInt(param.get('oid'));
      this.customerID = parseInt(param.get('cid'));
      this.getOrder();
    })
  }

  /**
   * Get order
   * 
   * @since 15/12/2018
   */
  getOrder() {
    this.total = 0;
    this.productService.getOrder(this.orderID, this.customerID).subscribe(order => {
      this.products = order;
      this.products.forEach((product) => {
        this.total += product.price * product.quantity;
        this.orderTitle += ', ' + product.name;
      });

      this.orderTitle += ')';
      this.orderTitle = this.orderID + ' (' + this.orderTitle.replace(', ', '');
    });
  }

  /**
   * Add quantity
   * 
   * @since 02/01/2019
   * @param product
   */
  addQuantity(product: Product) {
    this.order.id = this.orderID;
    this.order.productID = product.id;
    this.order.quantity = product.quantity + 1;
    this.orderService.update(this.order).subscribe(data => {
      product.quantity++;
    }, error => {
      console.log(error);
    })
  }

  /**
   * Substract quantity
   * 
   * @since 02/01/2019
   * @param product
   */
  subtractQuantity(product: Product) {
    if (product.quantity == 1) {
      return;
    }

    this.order.id = this.orderID;
    this.order.productID = product.id;
    this.order.quantity = product.quantity - 1;
    this.orderService.update(this.order).subscribe(data => {
      product.quantity--;
    }, error => {
      console.log(error);
    })
  }

  /**
   * Remove product from order
   * 
   * @since 15/12/2018
   * @param productID product ID
   * @param quantity product quantity
   * @param name product name
   */
  removeProduct(productID: number, quantity: number, name: string) {
    swal({
      type: 'question',
      title: 'Bạn có chắc muốn bỏ sản phẩm ' + name + '?',
      showCancelButton: true,
      confirmButtonText: 'Yes',
      cancelButtonText: 'No'
    }).then((result) => {
      if (result.value) {
        this.orderService.remove(this.orderID, productID, quantity).subscribe(data => {
          this.getOrder();
        }, error => {
          swal({
            type: 'error',
            title: 'Lỗi không thể xoá sản phẩm ' + name + '!'
          });

          console.log(error);
        });
      }
    });
  }

  /**
   * Check out order
   * 
   * @since 02/01/2019
   */
  delete() {
    swal({
      type: 'question',
      title: 'Bạn có chắc muốn xoá giỏ hàng?',
      showCancelButton: true,
      confirmButtonText: 'Yes',
      cancelButtonText: 'No'
    }).then((result) => {
      if (result.value) {
        this.orderService.delete(this.orderID).subscribe(data => {
          this.router.navigate(['/']);
        }, error => {
          swal({
            type: 'error',
            title: 'Lỗi không thể xoá sản phẩm ' + name + '!'
          });

          console.log(error);
        });
      }
    });
  }

  /**
   * Check out order
   * 
   * @since 15/12/2018
   */
  checkOut() {
    var order: Order = new Order;
    order.id = this.orderID;
    order.customerID = this.customerID;
    this.orderService.checkOut(order).subscribe(data => {
      this.getOrder();
      swal({
        type: 'success',
        title: 'Bạn đã thanh toán thành công đơn hàng!'
      });

      this.router.navigate(['']);
    }, error => {
      swal({
        type: 'error',
        title: 'Lỗi server!'
      });
    });
  }

}