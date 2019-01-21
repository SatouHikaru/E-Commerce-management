import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { WirelessRouterService } from '../../service/wireless.router.service';
import { WirelessRouter } from '../../model/wireless.router.class';
import { BrandService } from 'src/app/module/product/service/brand.service';
import { Brand } from 'src/app/module/product/model/brand.class';
import swal from 'sweetalert2';

@Component({
  selector: 'wireless-router-form',
  templateUrl: './wireless-router-form.component.html',
  styleUrls: ['./wireless-router-form.component.css']
})
export class WirelessRouterFormComponent implements OnInit {

  wirelessRouterForm: FormGroup;
  wirelessRouter: WirelessRouter = new WirelessRouter();
  brands: Brand[];
  title: string;
  image: string = 'assets/images/notfound.jpg';
  fileUpload: File;

  constructor(private router: Router, private location: Location,
      private formBuilder: FormBuilder, private wirelessRouterService: WirelessRouterService,
      private brandService: BrandService) { }

  ngOnInit() {
    if (localStorage.getItem('role') == 'customer') {
      this.router.navigate(['']);
    }

    this.wirelessRouterForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
      image: [''],
      price: ['', Validators.required],
      quantity: ['', Validators.required],
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
   * Submit wireless router form
   * @since 15/12/2018
   */
  onSubmit() {
    
  }

  /**
   * Add new wireless router
   * @since 15/12/2018
   */
  add() {
    this.wirelessRouter.brandID = this.wirelessRouterForm.get('brand').value;
    const formData = new FormData();
    formData.append('wirelessRouter', JSON.stringify(this.wirelessRouter));
    formData.append('fileUpload', this.fileUpload);

    this.wirelessRouterService.add(formData).subscribe(data => {
      swal({
        type: 'success',
        title: 'Added wirelessRouter [' + this.wirelessRouter.name +'] successfully!'
      });
    }, error => {
      swal({
        type: 'error',
        title: 'Add wirelessRouter [' + this.wirelessRouter.name +'] failed!'
      });
      console.log(error);
    });
  }

  /**
   * Update a wireless router
   * @since 15/12/2018
   */
  update() {
    this.wirelessRouter.brandID = this.wirelessRouterForm.get('brand').value;
    const formData = new FormData();
    formData.append('wirelessRouter', JSON.stringify(this.wirelessRouter));
    formData.append('fileUpload', this.fileUpload);

    this.wirelessRouterService.update(this.wirelessRouter.id, formData).subscribe(data => {
      swal({
        type: 'success',
        title: 'Updated wirelessRouter [' + this.wirelessRouter.id +'] successfully!'
      });
    }, error => {
      swal({
        type: 'error',
        title: 'Update wirelessRouter [' + this.wirelessRouter.id +'] failed!'
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