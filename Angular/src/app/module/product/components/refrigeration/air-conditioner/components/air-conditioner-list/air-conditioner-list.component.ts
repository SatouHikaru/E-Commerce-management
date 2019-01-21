import { Component, OnInit } from '@angular/core';
import { AirConditionerService } from '../../service/air.conditioner.service';
import { BrandService } from 'src/app/module/product/service/brand.service';
import { AirConditioner } from '../../model/air.conditioner.class';
import { Brand } from 'src/app/module/product/model/brand.class';

@Component({
  selector: '\air-conditioner-list',
  templateUrl: './air-conditioner-list.component.html',
  styleUrls: ['./air-conditioner-list.component.css']
})
export class AirConditionerListComponent implements OnInit {

  // Filter
  brands: Brand[]
  brandName: string[] = []
  countBrands = 0
  checkBoxAllBrands = false
  isSelectPrice = false
  priceFrom = 0
  priceTo = 0

  // Search
  keySearch = ''
  isSearched = false

  // Products
  totalItems: number
  currentPage = 1
  pagedItems: AirConditioner[]
  pager: any = {}
  isConnected = false

  constructor(private airConditionerService: AirConditionerService, private brandService: BrandService) { }

  ngOnInit() {
    this.countAll();
    this.getBrandList();
  }

  /**
   * Count list of wireless routers quantity
   * 
   * @since 27/12/2018
   */
  countAll() {
    this.airConditionerService.countAll().subscribe(quantity => {
      this.totalItems = quantity;
      this.isConnected = true;
    }, error => {
      console.log(error);
    });
  }

  /**
   * Count list of wireless routers quantity by filter
   * 
   * @since 27/12/2018
   */
  countByFilter() {
    this.airConditionerService.countByFilter(this.keySearch, this.brandName, this.priceFrom, this.priceTo).subscribe(quantity => {
      this.totalItems = quantity;
    }, error => {
      console.log(error);
    });
  }

  /**
   * Page changed
   * 
   * @since 27/12/2018
   * @param event
   */
  pageChanged(event) {
    this.currentPage = event.currentPage;
    
    if (this.isSearched) {
      this.getByFilter(this.currentPage - 1);
    } else {
      this.getPerPage(this.currentPage - 1);
    }
  }

  /**
   * Get list of wireless routers per page
   * 
   * @param page
   */
  getPerPage(page: number) {
    this.airConditionerService.getPerPage(page).subscribe(airConditioners => {
      this.pagedItems = airConditioners;
      this.isSearched = false;
    }, error => {
      console.log(error);
    });
  }

  /**
   * Get list of wireless routers by filter
   * 
   * @since 17/11/2018
   * @param page
   */
  getByFilter(page: number) {
    this.countByFilter();
    this.airConditionerService.getByFilter(this.keySearch, this.brandName, this.priceFrom, this.priceTo, page).subscribe(airConditioners => {
      this.pagedItems = airConditioners;
      this.isSearched = true;
    }, error => {
      console.log(error);
    });
  }


  getAll() {
    this.countAll();
    this.getPerPage(this.currentPage - 1);
  }

  /**
   * Get list of wireless routers by brand
   * 
   * @since 17/11/2018
   */
  getBrandList() {
    this.brandService.getAllByProductType('Điều hoà').subscribe(brands => {
      this.brands = brands;
      this.countBrands = brands.length;
    }, error => {
      console.log(error);
    });
  }

  /**
   * Check if selecting price
   * 
   * @since 02/01/2019
   */
  selectPrice(event) {
    if (event.target.checked) {
      this.isSelectPrice = true;
    } else {
      this.isSelectPrice = false;
      this.reset();
    }
  }

  /**
   * Check price range value
   * 
   * @since 17/11/2018
   */
  updatePriceRange() {
    if (this.priceFrom >= this.priceTo) {
      if (this.priceTo == 0) {
        this.priceFrom = 0;
      } else {
        this.priceFrom = this.priceTo - 10000;
      }
    }
  }

  /**
   * Reset price range value
   * 
   * @since 17/11/2018
   */
  reset() {
    this.priceFrom = 0;
    this.priceTo = 0;
  }

  /**
   * Select brand
   * 
   * @since 17/11/2018
   * @param event
   * @param name brand name
   */
  selectBrand(event, name: string) {
    if (event.target.checked) {
      this.brandName.push(name);
    } else {
      const index = this.brandName.findIndex((index => index == name))
      this.brandName.splice(index, 1);
    }

    this.checkBrandsIsEmpty();
  }

  /**
   * Check if all brands is selected
   *  
   * @since 02/01/2019
   * @param event
   */
  checkSelectAllBrands(event) {
    if (event.target.checked) {
      this.selectAllBrands();
    } else {
      this.deselectAllBrands();
    }
  }

  /**
   * Select all brands
   * 
   * @since 17/11/2018
   */
  selectAllBrands() {
    for (var i = 0; i < this.brands.length; i++) {
      if (this.brandName.indexOf(this.brands[i].name) < 0) {
        this.brandName.push(this.brands[i].name);
        this.brands[i].checked = true;
      }
    }
  }

  /**
   * Deselect all brands
   * 
   * @since 02/01/2019
   */
  deselectAllBrands() {
    this.brandName.splice(0, this.brandName.length);
    for (var id = 0; id < this.brands.length; id++) {
      this.brands[id].checked = false;
    }
  }

  /**
   * Check list of brands name is empty
   * 
   * @since 02/01/2019
   */
  checkBrandsIsEmpty() {
    if (this.brandName.length == this.countBrands) {
      this.checkBoxAllBrands = true;
    } else {
      this.checkBoxAllBrands = false;
    }
  }

}