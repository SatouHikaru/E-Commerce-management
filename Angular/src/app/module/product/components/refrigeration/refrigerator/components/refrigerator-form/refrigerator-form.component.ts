import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { RefrigeratorService } from '../../service/refrigerator.service';
import { BrandService } from 'src/app/module/product/service/brand.service';
import { Refrigerator } from '../../model/refrigerator.class';
import { Brand } from 'src/app/module/product/model/brand.class';
import swal from 'sweetalert2';

@Component({
  selector: 'refrigerator-form',
  templateUrl: './refrigerator-form.component.html',
  styleUrls: ['./refrigerator-form.component.css']
})
export class RefrigeratorFormComponent implements OnInit {

  refrigeratorForm: FormGroup;
  refrigerator: Refrigerator = new Refrigerator();
  brands: Brand[];
  title: string;
  image: string = 'assets/images/notfound.jpg';
  fileUpload: File;

  constructor(private router: Router, private location: Location,
      private formBuilder: FormBuilder, private refrigeratorService: RefrigeratorService,
      private brandService: BrandService) { }

  ngOnInit() {
    if (localStorage.getItem('role') == 'customer') {
      this.router.navigate(['']);
    }

    this.refrigeratorForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
      image: ['', Validators.required],
      price: ['', Validators.required],
      quantity: ['', Validators.required],
      
      size: ['', [Validators.required, Validators.maxLength(30)]],
      weight: [''],
      maxVolumetric: ['', Validators.required],
      freezerVolumetric: [''],
      iceCubesVolumetric: [''],
      coolingTechnology: ['', [Validators.required, Validators.maxLength(50)]],

      brand: ['', Validators.required],
      description: [''],
      countryOfOrigin: ['']
    });

    this.getBrandList();
  }

  /**
   * Get list of refrigerator brand
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
   * Submit refrigerator form
   * @since 15/12/2018
   */
  onSubmit() {
    
  }

  /**
   * Add new refrigerator
   * @since 15/12/2018
   */
  add() {
    this.refrigerator.brandID = this.refrigeratorForm.get('brand').value;
    const formData = new FormData();
    formData.append('refrigerator', JSON.stringify(this.refrigerator));
    formData.append('fileUpload', this.fileUpload);

    this.refrigeratorService.add(formData).subscribe(data => {
      swal({
        type: 'success',
        title: 'Added refrigerator [' + this.refrigerator.name +'] successfully!'
      });
    }, error => {
      swal({
        type: 'error',
        title: 'Add refrigerator [' + this.refrigerator.name +'] failed!'
      });
      console.log(error);
    });
  }

  /**
   * Update refrigerator
   * @since 15/12/2018
   */
  update() {
    this.refrigerator.brandID = this.refrigeratorForm.get('brand').value;
    const formData = new FormData();
    formData.append('refrigerator', JSON.stringify(this.refrigerator));
    formData.append('fileUpload', this.fileUpload);

    this.refrigeratorService.update(this.refrigerator.id, formData).subscribe(data => {
      swal({
        type: 'success',
        title: 'Updated refrigerator [' + this.refrigerator.id +'] successfully!'
      });
    }, error => {
      swal({
        type: 'error',
        title: 'Update refrigerator [' + this.refrigerator.id +'] failed!'
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