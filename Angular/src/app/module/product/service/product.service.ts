import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Proxy } from 'src/app/common/proxy.class';
import { Product } from '../model/product.class';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  productUrl = this.proxyUrl.baseUrl.concat('/products');
  orderUrl = this.proxyUrl.baseUrl.concat('/order');

  constructor(private http: HttpClient, private proxyUrl: Proxy) { }

  /**
   * Get new product
   *
   * @since 02/01/2019
   */
  public getNewProduct() {
    return this.http.get<Product[]>(this.productUrl);
  }

  /**
   * Get order detail
   *
   * @since 01/01/2019
   * @param orderID order ID
   */
  public getOrder(orderID: number, customerID: number) {
    return this.http.get<Product[]>(this.orderUrl + '/detail/' + orderID + "/" + customerID);
  }

  /**
	 * Revenue statistics
	 *
	 * @since  01/02/2019
	 * @param startDate
	 * @param endDate
	 */
  public statistic(startDate: Date, endDate: Date) {
    return this.http.get<Product[]>(this.proxyUrl.baseUrl + '/statistic/' + startDate + '/' + endDate);
  }
}