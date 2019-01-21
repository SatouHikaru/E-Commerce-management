import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CustomerService } from '../../service/customer.service';
import { AccountService } from 'src/app/module/account/service/account.service';
import { User } from 'src/app/module/account/model/user.class';
import { Account } from 'src/app/module/account/model/account.class';
import swal from 'sweetalert2';

@Component({
  selector: 'customer-detail',
  templateUrl: './customer-detail.component.html',
  styleUrls: ['./customer-detail.component.css']
})
export class CustomerDetailComponent implements OnInit {

  returnUrl = ''
  customerForm: FormGroup
  customer: User = new User
  account: Account = new Account
  avatar = ''
  fileUpload: File = null
  newPassword = ''
  confirmPassword = ''
  checked = false
  message = ''

  constructor(private router: Router, private activateRoute: ActivatedRoute, private location: Location,
    private formBuilder: FormBuilder, private customerService: CustomerService,
    private accountService: AccountService) { }

  ngOnInit() {
    if (localStorage.getItem('role') != 'customer') {
      this.router.navigate(['/admin']);
    }

    this.returnUrl = localStorage.getItem('url');
    const id = this.activateRoute.snapshot.params['id'];
    this.customerService.getById(id).subscribe(customer => {
      this.customer = customer;
      this.avatar = this.customer.avatar;
    }, error => {
      console.log(error);
    });

    this.customerForm = this.formBuilder.group({
      avatar: [''],
      userName: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(40)]],
      oldPassword: ['', [Validators.minLength(6), Validators.maxLength(40)]],
      newPassword: [''],
      confirmPassword: [''],
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(60)]],
      gender: ['', Validators.required],
      birthday: ['', Validators.required],
      phone: ['', Validators.required],
      email: ['', [Validators.required, Validators.pattern('[^ @]*@[^ @]*')]]
    });
  }

  /**
   * Preview image
   * 
   * @since 13/12/2018
   * @param event
   */
  previewImage(event) {
    this.fileUpload = event.target.files[0];
    const reader = new FileReader();
    reader.onload = (fileData) => {
      this.avatar = reader.result + '';
    }

    reader.readAsDataURL(this.fileUpload);
  }

  /**
   * Check if check change password checkbox
   * 
   * @since 02/01/2019
   * @param event
   */
  changePassword(event) {
    if (event.target.checked) {
      this.checked = true;
    } else {
      this.checked = false;
      this.newPassword = this.confirmPassword = '';
    }
  }

  /**
   * Check login
   * 
   * @since 02/01/2019
   */
  checkLogin(): Account {
    this.accountService.login(this.account).subscribe(account => {
      return account;
    }, error => {
      console.log(error);
    });

    return null;
  }

  /**
   * Check if check change password checkbox
   * 
   * @since 02/01/2019
   */
  update() {
    this.account.userName = this.customer.userName;
    this.accountService.login(this.account).subscribe(account => {
      if (account != null) {
        if (this.checked) {
          if (this.newPassword.match(this.confirmPassword) === null) {
            this.account.password = this.newPassword = this.confirmPassword = '';
            this.message = "Mật khẩu xác nhận chưa trùng khớp!";
            return;
          }
        }

        this.account.avatar = this.customerForm.get('avatar').value;
        const formData = new FormData();
        formData.append('account', JSON.stringify(this.account));
        if (this.fileUpload != null) {
          formData.append('fileUpload', this.fileUpload);
        }

        this.accountService.update(formData).subscribe(data => {
          this.customerService.update(this.customer).subscribe(data => {
            var image = this.account.avatar.split('\\');
            localStorage.removeItem('avatar');
            localStorage.setItem('avatar', 'assets/images/user/' + image[2]);
            swal({
              type: 'success',
              title: 'Thay đổi thông tin thành công!'
            });

            this.router.navigate([this.returnUrl]);
          }, error => {
            console.log(error);
          });
        }, error => {
          console.log(error);
        });
      } else {
        this.message = "Sai mật khẩu";
        this.account.password = this.newPassword = this.confirmPassword = '';
      }
    }, error => {
      console.log(error);
    });
  }

}