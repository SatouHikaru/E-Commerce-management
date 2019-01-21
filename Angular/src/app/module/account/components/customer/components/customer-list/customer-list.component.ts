import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from '../../service/customer.service';
import { User } from 'src/app/module/account/model/user.class';

@Component({
  selector: 'customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {

  isAdmin = true

  // Search
  keySearch = ''
  isSearched = false

  // Customers
  totalItems: number
  currentPage = 1
  pagedItems: User[]
  pager: any = {}
  isConnected = false
  idList: number[] = []
  isSelected = false;

  constructor(private router: Router, private customerService: CustomerService) { }

  ngOnInit() {
    if (localStorage.getItem('role') == 'customer') {
      this.router.navigate(['']);
    } else if (localStorage.getItem('role') == 'employee') {
      this.isAdmin = false;
    }

    this.countAll();
  }

  /**
   * Count list of customers quantity
   * 
   * @since 27/12/2018
   */
  countAll() {
    this.customerService.countAll().subscribe(quantity => {
      this.totalItems = quantity;
      this.isConnected = true;
    }, error => {
      console.log(error);
    });
  }

  /**
   * Count list of customers quantity by filter
   * 
   * @since 27/12/2018
   */
  countByFilter() {
    this.customerService.countByKeySearch(this.keySearch).subscribe(quantity => {
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
      this.getByKeySearch(this.currentPage - 1);
    } else {
      this.getPerPage(this.currentPage - 1);
    }
  }

  /**
   * Get list of customers per page
   * 
   * @since 15/12/2018
   * @param page
   */
  getPerPage(page: number) {
    this.customerService.getPerPage(page).subscribe(customers => {
      this.pagedItems = customers;
      this.isSearched = false;
    }, error => {
      console.log(error);
    });
  }

  /**
   * Get list of customers by filter
   * 
   * @since 17/11/2018
   * @param page
   */
  getByKeySearch(page: number) {
    this.countByFilter();
    this.customerService.getByKeySearch(this.keySearch, page).subscribe(customers => {
      this.pagedItems = customers;
      this.isSearched = true;
    }, error => {
      console.log(error);
    });
  }

  /**
   * Get list of customers per page
   * 
   * @since 02/01/2019
   */
  getAll() {
    this.countAll();
    this.getPerPage(this.currentPage - 1);
  }

}