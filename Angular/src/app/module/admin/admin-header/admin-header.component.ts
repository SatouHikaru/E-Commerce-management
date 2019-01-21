import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/common/auth/auth.service';
import { EmployeeService } from '../../account/components/employee/service/employee.service';

@Component({
  selector: 'admin-header',
  templateUrl: './admin-header.component.html',
  styleUrls: ['./admin-header.component.css']
})
export class AdminHeaderComponent implements OnInit {

  isLoggedIn: boolean = false
  url: string
  avatar: string
  isAdmin: boolean = false;

  constructor(private router: Router, public authService: AuthService,
      private employeeService: EmployeeService) { }

  ngOnInit() {
    if (localStorage.getItem('role') != 'customer') {
      if (localStorage.getItem('role') == 'admin') {
        this.isAdmin = true;
      }

      this.isLoggedIn = true;
      this.avatar = localStorage.getItem('avatar');
    } else {
      this.router.navigate(['']);
    }

    this.url = this.router.url;
  }

  /**
   * See info
   * 
   * @since 02/01/2019
   */
  seeInfo() {
    this.employeeService.getByUserName(localStorage.getItem('token')).subscribe(employee => {
      var id = employee.id;
      localStorage.setItem('url', this.url);
      this.router.navigate(['/admin/employees', id]);
    }, error => {
      console.log(error);
    });
  }

  /**
   * Logout
   * 
   * @since 02/01/2019
   */
  logout() {
    this.authService.removeLocalStorage();
    this.router.navigate(['']);
  }

}