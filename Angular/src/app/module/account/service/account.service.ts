import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Account } from '../model/account.class';
import { Proxy } from 'src/app/common/proxy.class';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  loginUrl = this.proxyUrl.baseUrl.concat('/login');
  url = this.proxyUrl.baseUrl.concat('/accounts');

  constructor(private http: HttpClient, private proxyUrl: Proxy) { }

  /**
   * Login
   * 
   * @since 02/01/2018
   */
  public login(account: Account) {
    return this.http.post<Account>(this.loginUrl, account);
  }

  /**
   * Count accounts quantity
   * 
   * @since 02/01/2018
   */
  public count(userName: string) {
    return this.http.get<number>(this.url + '/count?userName=' + userName);
  }

  /**
   * Get list of accounts per page
   *
   * @since 02/01/2018
   */
  public getPerPage(page: number) {
    return this.http.get<Account[]>(this.url + '/page/' + page);
  }

  /**
   * Get account by name
   *
   * @since 02/01/2018
   */
  public getByUserName(userName: string) {
    return this.http.get<Account>(this.url + '/' + userName);
  }

  /**
   * Add new account
   * 
   * @since 02/01/2018
   * @param account a new account
   */
  public add(account: Account) {
    return this.http.post(this.url, account);
  }

  /**
   * Update account
   * 
   * @since 02/01/2018
   * @param formData
   */
  public update(formData: FormData) {
    return this.http.post(this.url + '/change', formData);
  }

  /**
   * Delete account
   * 
   * @since 02/01/2018
   * @param userName user name
   */
  public delete(userName: string) {
    return this.http.delete(this.url + '/' + userName);
  }

}