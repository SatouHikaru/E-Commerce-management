import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Brand } from '../model/brand.class';
import { Proxy } from 'src/app/common/proxy.class';

@Injectable({
  providedIn: 'root'
})
export class BrandService {

  url = this.proxyUrl.baseUrl.concat('/brands');

  constructor(private http: HttpClient, private proxyUrl: Proxy) { }

  /**
   * Get list of Brands
   *
   * @since 17/11/2018
   */
  public getAll() {
    return this.http.get<Brand[]>(this.url);
  }

  /**
   * Get product list of Brands
   *
   * @since 17/11/2018
   */
  public getAllByProductType(productType: string) {
    return this.http.get<Brand[]>(this.url + '/filter?productType=' + productType);
  }

}