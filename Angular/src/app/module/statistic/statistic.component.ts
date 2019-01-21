import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import * as CanvasJS from '../../common/canvasjs.min';
import { ProductService } from '../product/service/product.service';
import { Product } from '../product/model/product.class';

@Component({
  selector: 'statistic',
  templateUrl: './statistic.component.html',
  styleUrls: ['./statistic.component.css']
})
export class StatisticComponent implements OnInit {

  startDate: Date
  endDate: Date
  products: Product[]
  televisionRevenue = 0
  dvdPlayerRevenue = 0
  washerRevenue = 0
  refrigeratorRevenue = 0
  airConditionerRevenue = 0
  riceCookerRevenue = 0
  wirelessRouterRevenue = 0
  televisionQuantity = 0
  dvdPlayerQuantity = 0
  washerQuantity = 0
  refrigeratorQuantity = 0
  airConditionerQuantity = 0
  riceCookerQuantity = 0
  wirelessRouterQuantity = 0
  PRODUCTS = []
  hasStatistic = false

  constructor(private router: Router, private productService: ProductService) { }

  ngOnInit() {
    if (localStorage.getItem('role') == 'employee') {
      this.router.navigate(['/admin']);
    } else if (localStorage.getItem('role') == 'customer') {
      this.router.navigate(['']);
    }
  }

  /**
   * Reset total and quantity values
   * 
   * @since 14/01/2019
   */
  reset() {
    this.televisionRevenue = 0
    this.dvdPlayerRevenue = 0
    this.washerRevenue = 0
    this.refrigeratorRevenue = 0
    this.airConditionerRevenue = 0
    this. riceCookerRevenue = 0
    this. wirelessRouterRevenue = 0
    this.televisionQuantity = 0
    this.dvdPlayerQuantity = 0
    this.washerQuantity = 0
    this.refrigeratorQuantity = 0
    this.airConditionerQuantity = 0
    this.riceCookerQuantity = 0
    this.wirelessRouterQuantity = 0
  }

  /**
   * Revenue statistics
   * 
   * @since 02/01/2019
   */
  statistic() {
    this.productService.statistic(this.startDate, this.endDate).subscribe(data => {
      this.reset();
      this.products = data;
      for (var i = 0; i < this.products.length; i++) {
        this.products[i].total = 0;
        this.products[i].total += this.products[i].price * this.products[i].quantity;
        if (this.products[i].productType == 'Ti vi') {
          this.televisionRevenue += this.products[i].total;
          this.televisionQuantity += this.products[i].quantity;
        } else if (this.products[i].productType == 'Đầu đĩa DVD') {
          this.dvdPlayerRevenue += this.products[i].total;
          this.dvdPlayerQuantity += this.products[i].quantity;
        } else if (this.products[i].productType == 'Máy giặt') {
          this.washerRevenue += this.products[i].total;
          this.washerQuantity += this.products[i].quantity;
        } else if (this.products[i].productType == 'Tủ lạnh') {
          this.refrigeratorRevenue += this.products[i].total;
          this.refrigeratorQuantity += this.products[i].quantity;
        } else if (this.products[i].productType == 'Điều hoà') {
          this.airConditionerRevenue += this.products[i].total;
          this.airConditionerQuantity += this.products[i].quantity;
        } else if (this.products[i].productType == 'Nồi cơm điện') {
          this.riceCookerRevenue += this.products[i].total;
          this.riceCookerQuantity += this.products[i].quantity;
        } else {
          this.wirelessRouterRevenue += this.products[i].total;
          this.wirelessRouterQuantity += this.products[i].quantity;
        }
      }

      this.PRODUCTS = [];
      this.PRODUCTS = [
        { productType: 'Ti vi', quantity: this.televisionQuantity, revenue: this.televisionRevenue },
        { productType: 'Đầu đĩa DVD', quantity: this.dvdPlayerQuantity, revenue: this.dvdPlayerRevenue },
        { productType: 'Máy giặt', quantity: this.washerQuantity, revenue: this.washerRevenue },
        { productType: 'Tủ lạnh', quantity: this.refrigeratorQuantity, revenue: this.refrigeratorRevenue },
        { productType: 'Điều hoà', quantity: this.airConditionerQuantity, revenue: this.airConditionerRevenue },
        { productType: 'Nồi cơm điện', quantity: this.riceCookerQuantity, revenue: this.riceCookerRevenue },
        { productType: 'Bộ định tuyến', quantity: this.wirelessRouterQuantity, revenue: this.wirelessRouterRevenue },
      ];

      this.hasStatistic = true;

      let chart = new CanvasJS.Chart("chartContainer", {
        theme: "light2",
        animationEnabled: true,
        exportEnabled: true,
        title: {
          text: "Doanh thu các loại sản phẩm"
        },
        data: [{
          type: "pie",
          showInLegend: true,
          toolTipContent: "<b>{name}</b>: ${y} (#percent%)",
          indexLabel: "{name} - #percent%",
          dataPoints: [
            { y: this.televisionRevenue, name: "Ti vi" },
            { y: this.dvdPlayerRevenue, name: "Đầu đĩa DVD" },
            { y: this.washerRevenue, name: "Máy giặt" },
            { y: this.refrigeratorRevenue, name: "Tủ lạnh" },
            { y: this.airConditionerRevenue, name: "Điều hoà" },
            { y: this.riceCookerRevenue, name: "Nồi cơm điện" },
            { y: this.wirelessRouterRevenue, name: "Bộ định tuyến" }
          ]
        }]
      });

      chart.render();
    }, error => {
      console.log(error);
    });
  }

}