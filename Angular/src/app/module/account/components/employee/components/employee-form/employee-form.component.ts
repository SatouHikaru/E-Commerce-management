import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AccountService } from 'src/app/module/account/service/account.service';
import { Account } from 'src/app/module/account/model/account.class';
import { EmployeeService } from '../../service/employee.service';
import { User } from 'src/app/module/account/model/user.class';
import swal from 'sweetalert2';

@Component({
  selector: 'employee-form',
  templateUrl: './employee-form.component.html',
  styleUrls: ['./employee-form.component.css']
})
export class EmployeeFormComponent implements OnInit {

  employeeForm: FormGroup
  account: Account = new Account
  employee: User = new User

  constructor(private location: Location, private formBuilder: FormBuilder, private router: Router,
    private accountService: AccountService, private employeeService: EmployeeService) { }

  ngOnInit() {
    if (localStorage.getItem('role') == 'customer') {
      this.router.navigate(['']);
    } else if (localStorage.getItem('role') == 'employee') {
      this.router.navigate(['/admin']);
    }
    
    this.employeeForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(60)]],
      gender: ['', Validators.required],
      birthday: ['', Validators.required],
      phone: ['', Validators.required],
      email: ['', [Validators.required, Validators.pattern('[^ @]*@[^ @]*')]]
    });
  }

  /**
   * Split name to account user name
   * 
   * @since 02/01/2019
   */
  splitName() {
    var names: string[] = this.employee.name.split(' ');
    this.account.userName = names[names.length - 1].toLowerCase();
    for (var i = 0; i < names.length - 1; i++) {
      this.account.userName += names[i].toLowerCase().substring(0, 1);
      this.account.password = this.account.userName;
    }

    this.checkUserNameIsDuplicate();
  }

  /**
   * Check user name is duplicate
   * 
   * @since 02/01/2019
   */
  checkUserNameIsDuplicate() {
    this.accountService.count(this.account.userName).subscribe(count => {
      if (count > 0) {
        this.account.userName += '' + count;
        this.account.password = this.account.userName;
      }
    }, error => {
      console.log(error);
    });
  }

  /**
   * Add employee
   * 
   * @since 02/01/2019
   */
  add() {
    this.account.roleID = 2;
    this.splitName();
    this.accountService.add(this.account).subscribe(account => {
      this.employee.userName = this.account.userName;
      this.employeeService.add(this.employee).subscribe(employee => {
        swal({
          type: 'success',
          title: 'Tài khoản ' + this.account.userName + ' đã được tạo!'
        });

        this.router.navigate(['/admin/employees']);
      }, error => {
        console.log(error);
      });
    }, error => {
      console.log(error);
    });
  }

}