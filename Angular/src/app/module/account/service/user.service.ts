import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user.class';
import { Observable } from 'rxjs';
import { Proxy } from 'src/app/common/proxy.class';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  url = this.proxyUrl.baseUrl.concat('/users');

  constructor(private http: HttpClient, private proxyUrl: Proxy) { }

  /**
   * Count users quantity
   * 
   * @since 29/12/2018
   */
  public countAll() {
    return this.http.get<number>(this.url + '/countAll');
  }

  /**
   * Count list of users quantity by filter
   * 
   * @since 27/12/2018
   * @param keySearch
   */
  count(keySearch: string) {
    return this.http.get<number>(this.url + '/count/' + keySearch);
  }

  /**
   * Get list of users per page
   *
   * @since 29/12/2018
   * @param page
   */
  public getPerPage(page: number) {
    return this.http.get<User[]>(this.url + '/page/' + page);
  }

  /**
   * Get list of users by key search
   *
   * @since 17/11/2018
   * @param keySearch
   * @param page
   */
  public get(keySearch: string, page: number) {
    return this.http.get<User[]>(this.url + '/search/' + page + '?keySearch=' + keySearch);
  }

  /**
   * Get user by name
   *
   * @since 17/11/2018
   * @param name
   */
  public getByName(name: string) {
    return this.http.get<User>(this.url + '/' + name);
  }

  /**
   * Add new user
   * 
   * @since 26/12/2018
   * @param formData
   */
  public add(formData: FormData): Observable<any> {
    return this.http.post(this.url, formData);
  }

  /**
   * Update user
   * 
   * @since 26/12/2018
   * @param id user ID
   * @param formData
   */
  public update(id: number, formData: FormData): Observable<any> {
    return this.http.post(this.url + '/' + id, formData);
  }

}