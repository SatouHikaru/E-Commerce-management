import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'admin-account-nav',
  templateUrl: './admin-account-nav.component.html',
  styleUrls: ['./admin-account-nav.component.css']
})
export class AdminAccountNavComponent implements OnInit {

  url: string

  constructor(private router: Router) { }

  ngOnInit() {
    this.url = this.router.url;
  }

}