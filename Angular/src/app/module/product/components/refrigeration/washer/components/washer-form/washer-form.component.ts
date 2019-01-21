import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { WasherService } from '../../service/washer.service';
import { BrandService } from 'src/app/module/product/service/brand.service';
import { Washer } from '../../model/washer.class';
import { Brand } from 'src/app/module/product/model/brand.class';
import swal from 'sweetalert2';

@Component({
  selector: 'washer-form',
  templateUrl: './washer-form.component.html',
  styleUrls: ['./washer-form.component.css']
})
export class WasherFormComponent implements OnInit {

  washerForm: FormGroup;
  washer: Washer = new Washer();
  brands: Brand[];
  title: string;
  image: string = 'assets/images/notfound.jpg';
  fileUpload: File;

  constructor(private router: Router, private location: Location,
      private formBuilder: FormBuilder, private washerService: WasherService,
      private brandService: BrandService) { }

  ngOnInit() {
    if (localStorage.getItem('role') == 'customer') {
      this.router.navigate(['']);
    }

    this.washerForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
      image: ['', Validators.required],
      price: ['', Validators.required],
      quantity: ['', Validators.required],
      
      type: ['', [Validators.required, Validators.maxLength(20)]],
      laundryCage: ['', [Validators.required, Validators.maxLength(20)]],
      spinSpeed: ['', Validators.required],
      engine: ['', [Validators.required, Validators.maxLength(50)]],
      size: ['', [Validators.required, Validators.maxLength(50)]],
      weight: [''],
      standardTechnology: ['', [Validators.required, Validators.maxLength(20)]],

      brand: ['', Validators.required],
      description: [''],
      countryOfOrigin: ['']
    });

    this.getBrandList();
  }

  /**
   * Get list of washer brand
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
   * Submit washer form
   * @since 15/12/2018
   */
  onSubmit() {
    
  }

  /**
   * Add new washer
   * @since 15/12/2018
   */
  add() {
    this.washer.brandID = this.washerForm.get('brand').value;
    const formData = new FormData();
    formData.append('washer', JSON.stringify(this.washer));
    formData.append('fileUpload', this.fileUpload);

    this.washerService.add(formData).subscribe(data => {
      swal({
        type: 'success',
        title: 'Added washer [' + this.washer.name +'] successfully!'
      });
    }, error => {
      swal({
        type: 'error',
        title: 'Add washer [' + this.washer.name +'] failed!'
      });
      console.log(error);
    });
  }

  /**
   * Update washer
   * @since 15/12/2018
   */
  update() {
    this.washer.brandID = this.washerForm.get('brand').value;
    const formData = new FormData();
    formData.append('washer', JSON.stringify(this.washer));
    formData.append('fileUpload', this.fileUpload);

    this.washerService.update(this.washer.id, formData).subscribe(data => {
      swal({
        type: 'success',
        title: 'Updated washer [' + this.washer.id +'] successfully!'
      });
    }, error => {
      swal({
        type: 'error',
        title: 'Update washer [' + this.washer.id +'] failed!'
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