import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeeService } from '../../service/employee.service';
import { AccountService } from 'src/app/module/account/service/account.service';
import { User } from 'src/app/module/account/model/user.class';
import swal from 'sweetalert2';

@Component({
  selector: 'employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  // Search
  keySearch = ''
  isSearched = false

  // Employees
  totalItems: number
  currentPage = 1
  pagedItems: User[]
  pager: any = {}
  isConnected = false
  userNameList: string[] = []
  isSelected = false;
  checkBoxAllEmployees: boolean = false

  constructor(private router: Router, private employeeService: EmployeeService,
      private accountService: AccountService) { }

  ngOnInit() {
    if (localStorage.getItem('role') == 'customer') {
      this.router.navigate(['']);
    } else if (localStorage.getItem('role') == 'employee') {
      this.router.navigate(['/admin']);
    }

    this.countAll();
  }

  /**
   * Count list of employees quantity
   * 
   * @since 27/12/2018
   */
  countAll() {
    this.employeeService.countAll().subscribe(quantity => {
      this.totalItems = quantity;
      this.isConnected = true;
    }, error => {
      console.log(error);
    });
  }

  /**
   * Count list of employees quantity by filter
   * 
   * @since 27/12/2018
   */
  countByFilter() {
    this.employeeService.countByKeySearch(this.keySearch).subscribe(quantity => {
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
   * Get list of employees per page
   * 
   * @since 15/12/2018
   * @param page
   */
  getPerPage(page: number) {
    this.employeeService.getPerPage(page).subscribe(employees => {
      this.pagedItems = employees;
      this.isSearched = false;
    }, error => {
      console.log(error);
    });
  }

  /**
   * Get list of employees by filter
   * 
   * @since 17/11/2018
   * @param page
   */
  getByKeySearch(page: number) {
    this.countByFilter();
    this.employeeService.getByKeySearch(this.keySearch, page).subscribe(employees => {
      this.pagedItems = employees;
      this.isSearched = true;
    }, error => {
      console.log(error);
    });
  }

  /**
   * Get list of employees per page
   * 
   * @since 02/01/2019
   */
  getAll() {
    this.countAll();
    this.getPerPage(this.currentPage - 1);
  }

  /**
   * Select employee
   * 
   * @since 02/01/2019
   * @param event
   * @param userName employee user name
   */
  selectEmployee(event, userName: string) {
    if (event.target.checked) {
      this.userNameList.push(userName);
      if (this.userNameList.length == 6) {
        this.checkBoxAllEmployees = true;
      }
    } else {
      const index = this.userNameList.findIndex((index => index == userName))
      this.userNameList.splice(index, 1);
      this.checkBoxAllEmployees = false;
    }

    this.checkEmployeesIsEmpty();
  }

  /**
   * Check if all employees is selected
   *  
   * @since 02/01/2019
   * @param event
   */
  checkSelectAllEmployees(event) {
    if (event.target.checked) {
      this.selectAllEmployees();
    } else {
      this.deselectAllEmployees();
    }
  }

  /**
   * Select all employees
   * 
   * @since 02/01/2019
   */
  selectAllEmployees() {
    for (var i = 0; i < this.pagedItems.length; i++) {
      if (this.userNameList.indexOf(this.pagedItems[i].userName) < 0) {
        this.userNameList.push(this.pagedItems[i].userName);
        this.pagedItems[i].checked = true;
      }

      this.isSelected = true;
    }
  }

  /**
   * Deselect all employees
   * 
   * @since 02/01/2019
   */
  deselectAllEmployees() {
    this.userNameList.splice(0, this.userNameList.length);
    for (var i = 0; i < this.pagedItems.length; i++) {
      this.pagedItems[i].checked = false;
    }

    this.isSelected = false;
  }

  /**
   * Check list of employees ID is empty
   * 
   * @since 02/01/2019
   */
  checkEmployeesIsEmpty() {
    if (this.userNameList.length > 0) {
      this.isSelected = true;
    } else {
      this.isSelected = false;
    }
  }

  /**
   * Delete employee(s) by ID
   * 
   * @since 02/01/2019
   */
  delete() {
    let userNameList = '';
    for (var i = 0; i < this.userNameList.length; i++) {
      userNameList += '[' + this.userNameList[i] + '], ';
    }

    userNameList = userNameList.slice(0, -2);
    swal({
      type: 'question',
      title: 'Bạn có chắc muốn xoá nhân viên có tài khoản ' + userNameList + ' ?',
      showCancelButton: true,
      confirmButtonText: 'Yes',
      cancelButtonText: 'No'
    }).then((result) => {
      if (result.value) {
        if (this.userNameList.length == 1) {   // Delete one employee
          this.accountService.delete(this.userNameList[0]).subscribe(data => {
            swal({
              type: 'success',
              title: 'Đã xoá nhân viên có tài khoản ' + userNameList + ' thành công!'
            });

            this.countAll();
          }, error => {
            swal({
              type: 'error',
              title: 'Xoá thất bại!'
            });
            console.log(error);
          });
        } else {   // Delete multiple employees
          for (var i = 0; i < this.userNameList.length; i++) {
            this.accountService.delete(this.userNameList[i]).subscribe(data => {
            }, error => {
              swal({
                type: 'error',
                title: 'Xoá thất bại!'
              });
              console.log(error);
              return;
            });
          }

          swal({
            type: 'success',
            title: 'Đã xoá nhân viên có mã ' + userNameList + ' thành công!'
          });
        }
      }
    });
  }

}