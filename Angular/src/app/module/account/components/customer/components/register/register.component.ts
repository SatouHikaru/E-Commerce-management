import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AccountService } from 'src/app/module/account/service/account.service';
import { Account } from 'src/app/module/account/model/account.class';
import { CustomerService } from '../../service/customer.service';
import { User } from 'src/app/module/account/model/user.class';
import swal from 'sweetalert2';

@Component({
  selector: 'register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup
  account: Account = new Account
  customer: User = new User
  password = ''
  accountMessage = '';
  passwordMessage = ''
  returnUrl: string

  constructor(private location: Location, private formBuilder: FormBuilder, private router: Router,
    private accountService: AccountService, private customerService: CustomerService) { }

  ngOnInit() {
    this.returnUrl = localStorage.getItem('url');

    this.registerForm = this.formBuilder.group({
      userName: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(40)]],
      password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(40)]],
      confirmPassword: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(40)]],
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(60)]],
      gender: ['', Validators.required],
      birthday: ['', Validators.required],
      phone: ['', Validators.required],
      email: ['', [Validators.required, Validators.pattern('[^ @]*@[^ @]*')]]
    });
  }

  /**
   * Check user name is duplicate
   * 
   * @since 02/01/2019
   */
  checkUserNameIsDuplicate() {
    this.accountService.count(this.account.userName).subscribe(count => {
      if (count == 1) {
        this.accountMessage = 'Tên tài khoản đã tồn tại!'
      } else {
        this.accountMessage = ''
      }
    }, error => {
      console.log(error);
    });
  }

  /**
   * Register
   * 
   * @since 02/01/2019
   */
  register() {
    this.account.roleID = 3;
    this.checkUserNameIsDuplicate();
    if (this.account.password.match(this.password) === null) {
      this.account.password = this.password = '';
      this.passwordMessage = "Mật khẩu xác nhận chưa trùng khớp!";
      return;
    }
    
    this.accountService.add(this.account).subscribe(account => {
      this.customer.userName = this.account.userName;
      this.customerService.add(this.customer).subscribe(customer => {
        swal({
          type: 'success',
          title: 'Tài khoản ' + this.account.userName + ' đã được tạo!'
        });

        localStorage.setItem('isLoggedIn', "true");
        localStorage.setItem('token', this.account.userName);
        localStorage.setItem('role', 'customer');
        localStorage.setItem('avatar', 'assets/images/avatar.jpg');

        this.removeUri();
        this.router.navigate([this.returnUrl]);
      }, error => {
        console.log(error);
      });
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