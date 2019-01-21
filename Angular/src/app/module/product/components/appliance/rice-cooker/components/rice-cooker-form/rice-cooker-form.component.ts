import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { RiceCookerService } from '../../service/rice.cooker.service';
import { BrandService } from 'src/app/module/product/service/brand.service';
import { RiceCooker } from '../../model/rice.cooker.class';
import { Brand } from 'src/app/module/product/model/brand.class';
import swal from 'sweetalert2';

@Component({
  selector: 'rice-cooker-form',
  templateUrl: './rice-cooker-form.component.html',
  styleUrls: ['./rice-cooker-form.component.css']
})
export class RiceCookerFormComponent implements OnInit {

  riceCookerForm: FormGroup;
  riceCooker: RiceCooker = new RiceCooker();
  brands: Brand[];
  title: string;
  image: string = 'assets/images/notfound.jpg';
  fileUpload: File;

  constructor(private router: Router, private location: Location,
      private formBuilder: FormBuilder, private riceCookerService: RiceCookerService,
      private brandService: BrandService) { }

  ngOnInit() {
    if (localStorage.getItem('role') == 'customer') {
      this.router.navigate(['']);
    }

    this.riceCookerForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
      image: ['', Validators.required],
      price: ['', Validators.required],
      quantity: ['', Validators.required],

      volumetric: ['', Validators.required],
      timer: ['', Validators.required],
      standardTechnology: ['', [Validators.required, Validators.maxLength(20)]],

      brand: ['', Validators.required],
      description: [''],
      countryOfOrigin: ['']
    });

    this.getBrandList();
  }

  /**
   * Get list of rice cooker brand
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
   * Submit rice cooker form
   * @since 15/12/2018
   */
  onSubmit() {
    
  }

  /**
   * Add new rice cooker
   * @since 15/12/2018
   */
  add() {
    this.riceCooker.brandID = this.riceCookerForm.get('brand').value;
    const formData = new FormData();
    formData.append('riceCooker', JSON.stringify(this.riceCooker));
    formData.append('fileUpload', this.fileUpload);

    this.riceCookerService.add(formData).subscribe(data => {
      swal({
        type: 'success',
        title: 'Added rice cooker [' + this.riceCooker.name +'] successfully!'
      });
    }, error => {
      swal({
        type: 'error',
        title: 'Add rice cooker [' + this.riceCooker.name +'] failed!'
      });
      console.log(error);
    });
  }

  /**
   * Update rice cooker
   * @since 15/12/2018
   */
  update() {
    this.riceCooker.brandID = this.riceCookerForm.get('brand').value;
    const formData = new FormData();
    formData.append('riceCooker', JSON.stringify(this.riceCooker));
    formData.append('fileUpload', this.fileUpload);

    this.riceCookerService.update(this.riceCooker.id, formData).subscribe(data => {
      swal({
        type: 'success',
        title: 'Updated rice cooker [' + this.riceCooker.id +'] successfully!'
      });
    }, error => {
      swal({
        type: 'error',
        title: 'Update rice cooker [' + this.riceCooker.id +'] failed!'
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