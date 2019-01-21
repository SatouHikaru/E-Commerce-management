import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'admin-product-nav',
  templateUrl: './admin-product-nav.component.html',
  styleUrls: ['./admin-product-nav.component.css']
})
export class AdminProductNavComponent implements OnInit {

  url: string

  constructor(private router: Router) { }

  ngOnInit() {
    this.url = this.router.url;
  }

}