import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { DVDPlayerService } from '../../service/dvd.player.service';
import { BrandService } from 'src/app/module/product/service/brand.service';
import { DVDPlayer } from '../../model/dvd.player.class';
import { Brand } from 'src/app/module/product/model/brand.class';
import swal from 'sweetalert2';

@Component({
  selector: 'dvd-player-form',
  templateUrl: './dvd-player-form.component.html',
  styleUrls: ['./dvd-player-form.component.css']
})
export class DVDPlayerFormComponent implements OnInit {

  dvdPlayerForm: FormGroup;
  dvdPlayer: DVDPlayer = new DVDPlayer();
  brands: Brand[];
  title: string;
  image: string = 'assets/images/notfound.jpg';
  fileUpload: File;

  constructor(private router: Router, private location: Location,
      private formBuilder: FormBuilder, private dvdPlayerService: DVDPlayerService,
      private brandService: BrandService) { }

  ngOnInit() {
    if (localStorage.getItem('role') == 'customer') {
      this.router.navigate(['']);
    }

    this.dvdPlayerForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
      image: ['', Validators.required],
      price: ['', Validators.required],
      quantity: ['', Validators.required],

      weight: [''],
      usb: ['', Validators.required],
      size: ['', [Validators.required, Validators.minLength(12), Validators.maxLength(20)]],
      
      brand: ['', Validators.required],
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
   * Submit DVD player form
   * @since 15/12/2018
   */
  onSubmit() {
    
  }

  /**
   * Add new DVD player
   * @since 15/12/2018
   */
  add() {
    this.dvdPlayer.brandID = this.dvdPlayerForm.get('brand').value;
    const formData = new FormData();
    formData.append('dvdPlayer', JSON.stringify(this.dvdPlayer));
    formData.append('fileUpload', this.fileUpload);

    this.dvdPlayerService.add(formData).subscribe(data => {
      swal({
        type: 'success',
        title: 'Added DVD player [' + this.dvdPlayer.name +'] successfully!'
      });
    }, error => {
      swal({
        type: 'error',
        title: 'Add DVD player [' + this.dvdPlayer.name +'] failed!'
      });
      console.log(error);
    });
  }

  /**
   * Update DVD player
   * @since 15/12/2018
   */
  update() {
    this.dvdPlayer.brandID = this.dvdPlayerForm.get('brand').value;
    const formData = new FormData();
    formData.append('dvdPlayer', JSON.stringify(this.dvdPlayer));
    formData.append('fileUpload', this.fileUpload);

    this.dvdPlayerService.update(this.dvdPlayer.id, formData).subscribe(data => {
      swal({
        type: 'success',
        title: 'Thêm sản phẩm ' + this.dvdPlayer.name + ' thành công!'
      });

      this.router.navigate(['/admin/electrics/dvd-players']);
    }, error => {
      swal({
        type: 'error',
        title: 'Thêm sản phẩm thất bại!'
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