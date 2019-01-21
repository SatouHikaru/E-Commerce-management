import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AirConditionerService } from '../../service/air.conditioner.service';
import { BrandService } from 'src/app/module/product/service/brand.service';
import { AirConditioner } from '../../model/air.conditioner.class';
import { Brand } from 'src/app/module/product/model/brand.class';
import swal from 'sweetalert2';

@Component({
  selector: 'air-conditioner-form',
  templateUrl: './air-conditioner-form.component.html',
  styleUrls: ['./air-conditioner-form.component.css']
})
export class AirConditionerFormComponent implements OnInit {

  airConditionerForm: FormGroup;
  airConditioner: AirConditioner = new AirConditioner();
  brands: Brand[];
  title: string;
  image: string = 'assets/images/notfound.jpg';
  fileUpload: File;

  constructor(private router: Router, private location: Location,
      private formBuilder: FormBuilder, private airConditionerService: AirConditionerService,
      private brandService: BrandService) { }

  ngOnInit() {
    if (localStorage.getItem('role') == 'customer') {
      this.router.navigate(['']);
    }

    this.airConditionerForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
      image: ['', Validators.required],
      price: ['', Validators.required],
      quantity: ['', Validators.required],
      
      weight: [''],
      roomVolume: ['', [Validators.required, Validators.maxLength(20)]],
      coldCapacity: ['', [Validators.required, Validators.maxLength(20)]],
      gas: ['', [Validators.required, Validators.maxLength(10)]],

      brand: ['', Validators.required],
      description: [''],
      countryOfOrigin: ['']
    });

    this.getBrandList();
  }

  /**
   * Get list of air conditioner brand
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
   * Submit air conditioner form
   * @since 15/12/2018
   */
  onSubmit() {
    
  }

  /**
   * Add new air conditioner
   * @since 15/12/2018
   */
  add() {
    this.airConditioner.brandID = this.airConditionerForm.get('brand').value;
    const formData = new FormData();
    formData.append('airConditioner', JSON.stringify(this.airConditioner));
    formData.append('fileUpload', this.fileUpload);

    this.airConditionerService.add(formData).subscribe(data => {
      swal({
        type: 'success',
        title: 'Added air conditioner [' + this.airConditioner.name +'] successfully!'
      });
    }, error => {
      swal({
        type: 'error',
        title: 'Add air conditioner [' + this.airConditioner.name +'] failed!'
      });
      console.log(error);
    });
  }

  /**
   * Update air conditioner
   * @since 15/12/2018
   */
  update() {
    this.airConditioner.brandID = this.airConditionerForm.get('brand').value;
    const formData = new FormData();
    formData.append('airConditioner', JSON.stringify(this.airConditioner));
    formData.append('fileUpload', this.fileUpload);

    this.airConditionerService.update(this.airConditioner.id, formData).subscribe(data => {
      swal({
        type: 'success',
        title: 'Updated air conditioner [' + this.airConditioner.id +'] successfully!'
      });
    }, error => {
      swal({
        type: 'error',
        title: 'Update air conditioner [' + this.airConditioner.id +'] failed!'
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