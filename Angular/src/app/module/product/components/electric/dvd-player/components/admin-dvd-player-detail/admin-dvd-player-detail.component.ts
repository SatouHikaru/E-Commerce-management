import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { DVDPlayerService } from '../../service/dvd.player.service';
import { BrandService } from 'src/app/module/product/service/brand.service';
import { DVDPlayer } from '../../model/dvd.player.class';
import { Brand } from 'src/app/module/product/model/brand.class';
import swal from 'sweetalert2';

@Component({
  selector: 'admin-dvd-player-detail',
  templateUrl: './admin-dvd-player-detail.component.html',
  styleUrls: ['./admin-dvd-player-detail.component.css']
})
export class AdminDVDPlayerDetailComponent implements OnInit {

  dvdPlayerForm: FormGroup;
  dvdPlayer: DVDPlayer = new DVDPlayer();
  brands: Brand[];
  brandID: number
  title: string;
  image: string = 'assets/images/notfound.jpg';
  fileUpload: File;
  quantity: number = 0;

  constructor(private router: Router, private activateRoute: ActivatedRoute, private location: Location,
      private formBuilder: FormBuilder, private dvdPlayerService: DVDPlayerService,
      private brandService: BrandService) { }

  ngOnInit() {
    if (localStorage.getItem('role') == 'customer') {
      this.router.navigate(['']);
    }

    const name = this.activateRoute.snapshot.params['name'];
    this.dvdPlayerService.getByName(name).subscribe(dvdPlayer => {
      this.dvdPlayer = dvdPlayer;
      this.brandID = this.dvdPlayer.brandID;
      this.image = this.dvdPlayer.image;
    });

    this.dvdPlayerForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
      image: [''],
      price: ['', Validators.required],
      quantity: ['', Validators.required],

      weight: [''],
      usb: ['', Validators.required],
      size: ['', [Validators.required, Validators.minLength(12), Validators.maxLength(20)]],
      
      brand: [''],
      description: [''],
      countryOfOrigin: ['']
    });

    this.getBrandList();
  }

  /**
   * Get list of wireless router brand
   * 
   * @since 17/11/2018
   */
  getBrandList() {
    this.brandService.getAll().subscribe(brands => {
      this.brands = brands;
    }, error => {
      console.log(error);
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
      this.image = reader.result + '';
    }

    reader.readAsDataURL(this.fileUpload);
  }

  /**
   * Update DVD player
   * @since 15/12/2018
   */
  update() {
    this.dvdPlayer.quantity = this.dvdPlayer.quantity + this.quantity;
    this.dvdPlayer.brandID = this.dvdPlayerForm.get('brand').value;
    const formData = new FormData();
    formData.append('dvdPlayer', JSON.stringify(this.dvdPlayer));
    if (this.fileUpload != null) {
      formData.append('fileUpload', this.fileUpload);
    }

    this.dvdPlayerService.update(this.dvdPlayer.id, formData).subscribe(data => {
      swal({
        type: 'success',
        title: 'Thay đổi thông tin sản phẩm ' + this.dvdPlayer.name + ' thành công!'
      });

      this.router.navigate(['/admin/electrics/dvd-players']);
    }, error => {
      swal({
        type: 'error',
        title: 'Thay đổi thông tin sản phẩm thất bại!'
      });
      console.log(error);
    });
  }

  /**
   * Go back to previous page
   * 
   * @since 02/01/2019
   */
  goBack() {
    this.location.back();
  }

}