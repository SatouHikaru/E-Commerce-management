import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../../../model/user.class';
import { Proxy } from 'src/app/common/proxy.class';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  url = this.proxyUrl.baseUrl.concat('/customers');

  constructor(private http: HttpClient, private proxyUrl: Proxy) { }

  /**
   * Count list of customer quantity
   * 
   * @since 29/12/2018
   */
  public countAll() {
    return this.http.get<number>(this.url + '/countAll');
  }

  /**
   * Count customers quantity
   * 
   * @since 02/01/2018
   */
  public countByKeySearch(keySearch: string) {
    return this.http.get<number>(this.url + '/count?keySearch=' + keySearch);
  }

  /**
   * Get list of customers per page
   *
   * @since 02/01/2018
   * @param page
   */
  public getPerPage(page: number) {
    return this.http.get<User[]>(this.url + '/page/' + page);
  }

  /**
   * Get list of customers by key search
   *
   * @since 02/01/2018
   * @param key search
   * @param page
   */
  public getByKeySearch(keySearch: string, page: number) {
    return this.http.get<User[]>(this.url + '/search/' + page + '?keySearch=' + keySearch);
  }

  /**
   * Get customer by ID
   *
   * @since 02/01/2018
   * @param id
   */
  public getById(id: number) {
    return this.http.get<User>(this.url + '/id/' + id);
  }

  /**
   * Get customer by user name
   *
   * @since 02/01/2018
   * @param userName
   */
  public getByUserName(userName: string) {
    return this.http.get<User>(this.url + '/userName/' + userName);
  }

  /**
   * Add new customer
   * 
   * @since 02/01/2018
   * @param customer new customer
   */
  public add(customer: User) {
    return this.http.post(this.url, customer);
  }

  /**
   * Update customer
   * 
   * @since 02/01/2018
   * @param customer customer need updating
   */
  public update(customer: User) {
    return this.http.put(this.url + '/' + customer.id, customer);
  }

  /**
   * Delete customer
   * 
   * @since 02/01/2018
   * @param customerName customer name
   */
  public delete(id: number) {
    return this.http.delete(this.url + '/' + id);
  }

}