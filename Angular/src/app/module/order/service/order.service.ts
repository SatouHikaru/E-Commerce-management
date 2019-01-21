import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Proxy } from 'src/app/common/proxy.class';
import { Order } from '../model/order.class';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  url = this.proxyUrl.baseUrl.concat('/orders');

  constructor(private http: HttpClient, private proxyUrl: Proxy) { }

  /**
   * Count list of orders
   * 
   * @since 29/12/2018
   */
  public countAll() {
    return this.http.get<number>(this.url + '/countAll');
  }

  /**
   * Get list of orders per page
   *
   * @since 29/12/2018
   */
  public getPerPage(page: number) {
    return this.http.get<Order[]>(this.url + '/page/' + page);
  }

  /**
   * Get list of orders
   *
   * @since 02/01/2019
   */
  public getList(customerID: number) {
    return this.http.get<Order[]>(this.url + '/customers/' + customerID);
  }

  /**
   * Get order by customer ID
   *
   * @since 02/01/2019
   */
  public getByCustomerID(customerID: number) {
    return this.http.get<Order>(this.url + '?customerID=' + customerID);
  }

  /**
   * Add new order
   * 
   * @since 17/11/2018
   * @param order new order
   */
  public add(order: Order) {
    return this.http.post(this.url, order);
  }

  /**
   * Add new product to order
   * 
   * @since 02/01/2019
   * @param orderID order ID
   * @param productID product ID
   * @param quantity product quantity
   */
  public addNewProductToOrder(order: Order) {
    return this.http.post(this.url + '/new', order);
  }

  /**
   * Update order
   * 
   * @since 17/11/2018
   * @param order order need updating
   */
  public update(order: Order) {
    return this.http.put(this.url + '/' + order.id, order);
  }

  /**
   * Check out order
   * 
   * @since 17/11/2018
   * @param order order need updating
   */
  public checkOut(order: Order) {
    return this.http.put(this.url + '/checkout/' + order.id, order);
  }

  /**
   * Remove product from order
   * 
   * @since 17/11/2018
   * @param orderID order ID
   * @param productID product ID
   * @param quantity product quantity
   */
  public remove(orderID: number, productID: number, quantity: number) {
    return this.http.delete(this.url + '/' + orderID + '/' + productID + '/' + quantity);
  }

  /**
   * Delete order
   * 
   * @since 17/11/2018
   * @param ID order ID
   */
  public delete(id: number) {
    return this.http.delete(this.url + '/' + id);
  }

}