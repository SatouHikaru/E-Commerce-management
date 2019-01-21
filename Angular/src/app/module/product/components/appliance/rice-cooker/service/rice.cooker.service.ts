import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RiceCooker } from '../model/rice.cooker.class';
import { Proxy } from 'src/app/common/proxy.class';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RiceCookerService {

  url = this.proxyUrl.baseUrl.concat('/riceCookers');

  constructor(private http: HttpClient, private proxyUrl: Proxy) { }

  /**
   * Count rice cookers quantity
   * 
   * @since 29/12/2018
   */
  public countAll() {
    return this.http.get<number>(this.url + '/countAll');
  }

  /**
   * Count list of rice cookers quantity by filter
   * 
   * @since 27/12/2018
   * @param keySearch
   * @param brands
   * @param priceFrom
   * @param priceTo
   */
  countByFilter(keySearch: string, brands: string[], priceFrom: number, priceTo: number) {
    return this.http.get<number>(this.url + '/countFilter?keySearch=' + keySearch + '&brands=' + brands +
      '&priceFrom=' + priceFrom + '&priceTo=' + priceTo);
  }

  /**
   * Count list of rice cookers quantity by filter
   * 
   * @since 27/12/2018
   * @param keySearch
   */
  count(keySearch: string) {
    return this.http.get<number>(this.url + '/count/' + keySearch);
  }

  /**
   * Get list of rice cookers per page
   *
   * @since 29/12/2018
   * @param page
   */
  public getPerPage(page: number) {
    return this.http.get<RiceCooker[]>(this.url + '/page/' + page);
  }

  /**
   * Get list of rice cookers by brand
   *
   * @since 17/11/2018
   * @param keySearch
   * @param brands
   * @param priceFrom
   * @param priceTo
   * @param page
   */
  public getByFilter(keySearch: string, brands: string[], priceFrom: number, priceTo: number, page: number) {
    return this.http.get<RiceCooker[]>(this.url + '/filter/' + page + '?keySearch=' + keySearch + '&brands=' + brands +
      '&priceFrom=' + priceFrom + '&priceTo=' + priceTo);
  }

  /**
   * Get list of rice cookers by key search
   *
   * @since 17/11/2018
   * @param keySearch
   * @param page
   */
  public get(keySearch: string, page: number) {
    return this.http.get<RiceCooker[]>(this.url + '/search/' + page + '?keySearch=' + keySearch);
  }

  /**
   * Get rice cooker by name
   *
   * @since 17/11/2018
   * @param name
   */
  public getByName(name: string) {
    return this.http.get<RiceCooker>(this.url + '/' + name);
  }

  /**
   * Add new rice cooker
   * 
   * @since 26/12/2018
   * @param formData
   */
  public add(formData: FormData): Observable<any> {
    return this.http.post(this.url, formData);
  }

  /**
   * Update rice cooker
   * 
   * @since 26/12/2018
   * @param id rice cooker ID
   * @param formData
   */
  public update(id: number, formData: FormData): Observable<any> {
    return this.http.post(this.url + '/' + id, formData);
  }

  /**
   * Delete rice cooker
   * 
   * @since 17/11/2018
   * @param id riceCooker ID
   */
  public delete(id: number) {
    return this.http.delete(this.url + '/' + id);
  }

}