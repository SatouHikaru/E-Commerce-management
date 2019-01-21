import { Component, OnInit } from '@angular/core';
import { Product } from '../product/model/product.class';
import { ProductService } from '../product/service/product.service';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  products: Product[]

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.getNewProduct();
  }

  /**
   * Get new product
   *
   * @since 02/01/2019
   */
  getNewProduct() {
    this.productService.getNewProduct().subscribe(products => {
      this.products = products;
    }, error => {
      console.log(error);
    });
  }

}