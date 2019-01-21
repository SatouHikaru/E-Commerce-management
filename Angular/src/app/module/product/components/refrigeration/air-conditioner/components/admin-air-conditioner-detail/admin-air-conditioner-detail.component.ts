import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AirConditionerService } from '../../service/air.conditioner.service';
import { BrandService } from 'src/app/module/product/service/brand.service';
import { AirConditioner } from '../../model/air.conditioner.class';
import { Brand } from 'src/app/module/product/model/brand.class';
import swal from 'sweetalert2';

@Component({
  selector: 'app-admin-air-conditioner-detail',
  templateUrl: './admin-air-conditioner-detail.component.html',
  styleUrls: ['./admin-air-conditioner-detail.component.css']
})
export class AdminAirConditionerDetailComponent implements OnInit {

  airConditionerForm: FormGroup;
  airConditioner: AirConditioner = new AirConditioner()
  brands: Brand[]
  brandID: number
  title: string
  image: string = 'assets/images/notfound.jpg'
  fileUpload: File
  quantity: number = 0

  constructor(private router: Router, private activateRoute: ActivatedRoute, private location: Location,
      private formBuilder: FormBuilder, private airConditionerService: AirConditionerService,
      private brandService: BrandService) { }

  ngOnInit() {
    if (localStorage.getItem('role') == 'customer') {
      this.router.navigate(['']);
    }

    const name = this.activateRoute.snapshot.params['name'];
    this.airConditionerService.getByName(name).subscribe(airConditioner => {
      this.airConditioner = airConditioner;
      this.brandID = this.airConditioner.brandID;
      this.image = this.airConditioner.image;
    });

    this.airConditionerForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
      image: [''],
      price: ['', Validators.required],
      quantity: ['', Validators.required],
      
      weight: [''],
      roomVolume: ['', [Validators.required, Validators.maxLength(20)]],
      coldCapacity: ['', [Validators.required, Validators.maxLength(20)]],
      gas: ['', [Validators.required, Validators.maxLength(10)]],

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
    this.airConditioner.quantity = this.airConditioner.quantity + this.quantity;
    this.airConditioner.brandID = this.airConditionerForm.get('brand').value;
    const formData = new FormData();
    formData.append('airConditioner', JSON.stringify(this.airConditioner));
    if (this.fileUpload != null) {
      formData.append('fileUpload', this.fileUpload);
    }

    this.airConditionerService.update(this.airConditioner.id, formData).subscribe(data => {
      swal({
        type: 'success',
        title: 'Thay đổi thông tin sản phẩm ' + this.airConditioner.name + ' thành công!'
      });

      this.router.navigate(['/admin/refrigerations/air-conditioners']);
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