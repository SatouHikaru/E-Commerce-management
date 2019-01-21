import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DVDPlayer } from '../model/dvd.player.class';
import { Proxy } from 'src/app/common/proxy.class';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DVDPlayerService {

  url = this.proxyUrl.baseUrl.concat('/dvdPlayers');

  constructor(private http: HttpClient, private proxyUrl: Proxy) { }

  /**
   * Count DVD players quantity
   * 
   * @since 29/12/2018
   */
  public countAll() {
    return this.http.get<number>(this.url + '/countAll');
  }

  /**
   * Count list of DVD players quantity by filter
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
   * Count list of DVD players quantity by filter
   * 
   * @since 27/12/2018
   * @param keySearch
   */
  count(keySearch: string) {
    return this.http.get<number>(this.url + '/count/' + keySearch);
  }

  /**
   * Get list of DVD players per page
   *
   * @since 29/12/2018
   * @param page
   */
  public getPerPage(page: number) {
    return this.http.get<DVDPlayer[]>(this.url + '/page/' + page);
  }

  /**
   * Get list of DVD players by brand
   *
   * @since 17/11/2018
   * @param keySearch
   * @param brands
   * @param priceFrom
   * @param priceTo
   * @param page
   */
  public getByFilter(keySearch: string, brands: string[], priceFrom: number, priceTo: number, page: number) {
    return this.http.get<DVDPlayer[]>(this.url + '/filter/' + page + '?keySearch=' + keySearch + '&brands=' + brands +
      '&priceFrom=' + priceFrom + '&priceTo=' + priceTo);
  }

  /**
   * Get list of DVD players by key search
   *
   * @since 17/11/2018
   * @param keySearch
   * @param page
   */
  public get(keySearch: string, page: number) {
    return this.http.get<DVDPlayer[]>(this.url + '/search/' + page + '?keySearch=' + keySearch);
  }

  /**
   * Get DVD player by name
   *
   * @since 17/11/2018
   * @param name
   */
  public getByName(name: string) {
    return this.http.get<DVDPlayer>(this.url + '/' + name);
  }

  /**
   * Add new DVD player
   * 
   * @since 26/12/2018
   * @param formData
   */
  public add(formData: FormData): Observable<any> {
    return this.http.post(this.url, formData);
  }

  /**
   * Update DVD player
   * 
   * @since 26/12/2018
   * @param id DVD player ID
   * @param formData
   */
  public update(id: number, formData: FormData): Observable<any> {
    return this.http.post(this.url + '/' + id, formData);
  }

  /**
   * Delete DVD player
   * 
   * @since 17/11/2018
   * @param id dvdPlayer ID
   */
  public delete(id: number) {
    return this.http.delete(this.url + '/' + id);
  }

}