import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AccountService } from '../../service/account.service';
import { Account } from '../../model/account.class';
import { CustomerService } from '../customer/service/customer.service';
import { User } from '../../model/user.class';

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  account: Account = new Account;
  message = '';
  returnUrl: string;

  constructor(private location: Location, private formBuilder: FormBuilder, private router: Router,
      private accountService: AccountService, private customerService: CustomerService) { }

  ngOnInit() {
    this.returnUrl = localStorage.getItem('url');

    this.loginForm = this.formBuilder.group({
      userName: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(40)]],
      password: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(40)]]
    });
  }

  /**
   * Login
   * 
   * @since 02/01/2019
   */
  login() {
    this.accountService.login(this.loginForm.value).subscribe(account => {
      if (account != null) {
        localStorage.setItem('isLoggedIn', "true");
        localStorage.setItem('token', account.userName);
        localStorage.setItem('role', account.roleName);
        localStorage.setItem('avatar', account.avatar);
  
        this.removeUri();
        if (account.roleName == 'customer') {
          this.router.navigate([this.returnUrl]);
        } else {
          this.router.navigate(['/admin']);
        }
      } else {
        this.message = "Tên đăng nhập hoặc mật khẩu chưa đúng!";
      }
    }, error => {
      console.log(error);
    });
  }

  /**
   * Go back to previous page
   * 
   * @since 02/01/2019
   */
  goBack() {
    this.removeUri();
    this.location.back();
  }

  /**
   * Remove URI
   * 
   * @since 02/01/2019
   */
  removeUri() {
    localStorage.removeItem('url');
  }

}
