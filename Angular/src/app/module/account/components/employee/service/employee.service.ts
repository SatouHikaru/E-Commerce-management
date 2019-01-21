import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../../../model/user.class';
import { Proxy } from 'src/app/common/proxy.class';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  url = this.proxyUrl.baseUrl.concat('/employees');

  constructor(private http: HttpClient, private proxyUrl: Proxy) { }

  /**
   * Count list of employee quantity
   * 
   * @since 29/12/2018
   */
  public countAll() {
    return this.http.get<number>(this.url + '/countAll');
  }

  /**
   * Count employees quantity
   * 
   * @since 02/01/2018
   */
  public countByKeySearch(keySearch: string) {
    return this.http.get<number>(this.url + '/count?keySearch=' + keySearch);
  }

  /**
   * Get list of employees per page
   *
   * @since 02/01/2018
   * @param page
   */
  public getPerPage(page: number) {
    return this.http.get<User[]>(this.url + '/page/' + page);
  }

  /**
   * Get list of employees by key search
   *
   * @since 02/01/2018
   * @param key search
   * @param page
   */
  public getByKeySearch(keySearch: string, page: number) {
    return this.http.get<User[]>(this.url + '/search/' + page + '?keySearch=' + keySearch);
  }

  /**
   * Get employee by ID
   *
   * @since 02/01/2018
   * @param id
   */
  public getById(id: number) {
    return this.http.get<User>(this.url + '/id/' + id);
  }

  /**
   * Get employee by user name
   *
   * @since 02/01/2018
   * @param userName
   */
  public getByUserName(userName: string) {
    return this.http.get<User>(this.url + '/' + userName);
  }

  /**
   * Add new employee
   * 
   * @since 02/01/2018
   * @param employee new employee
   */
  public add(employee: User) {
    return this.http.post(this.url, employee);
  }

  /**
   * Update employee
   * 
   * @since 02/01/2018
   * @param employee employee need updating
   */
  public update(employee: User) {
    return this.http.put(this.url + '/' + employee.id, employee);
  }

}