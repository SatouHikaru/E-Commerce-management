import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { RefrigeratorService } from '../../service/refrigerator.service';
import { BrandService } from 'src/app/module/product/service/brand.service';
import { Refrigerator } from '../../model/refrigerator.class';
import { Brand } from 'src/app/module/product/model/brand.class';
import swal from 'sweetalert2';

@Component({
  selector: 'app-admin-refrigerator-detail',
  templateUrl: './admin-refrigerator-detail.component.html',
  styleUrls: ['./admin-refrigerator-detail.component.css']
})
export class AdminRefrigeratorDetailComponent implements OnInit {

  refrigeratorForm: FormGroup;
  refrigerator: Refrigerator = new Refrigerator()
  brands: Brand[]
  brandID: number
  title: string
  image: string = 'assets/images/notfound.jpg'
  fileUpload: File
  quantity: number = 0

  constructor(private router: Router, private activateRoute: ActivatedRoute, private location: Location,
      private formBuilder: FormBuilder, private refrigeratorService: RefrigeratorService,
      private brandService: BrandService) { }

  ngOnInit() {
    if (localStorage.getItem('role') == 'customer') {
      this.router.navigate(['']);
    }

    const name = this.activateRoute.snapshot.params['name'];
    this.refrigeratorService.getByName(name).subscribe(refrigerator => {
      this.refrigerator = refrigerator;
      this.brandID = this.refrigerator.brandID;
      this.image = this.refrigerator.image;
    });

    this.refrigeratorForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
      image: [''],
      price: ['', Validators.required],
      quantity: ['', Validators.required],
      
      size: ['', [Validators.required, Validators.maxLength(30)]],
      weight: [''],
      maxVolumetric: ['', Validators.required],
      freezerVolumetric: [''],
      iceCubesVolumetric: [''],
      coolingTechnology: ['', [Validators.required, Validators.maxLength(50)]],

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
    this.refrigerator.quantity = this.refrigerator.quantity + this.quantity;
    this.refrigerator.brandID = this.refrigeratorForm.get('brand').value;
    const formData = new FormData();
    formData.append('refrigerator', JSON.stringify(this.refrigerator));
    if (this.fileUpload != null) {
      formData.append('fileUpload', this.fileUpload);
    }

    this.refrigeratorService.update(this.refrigerator.id, formData).subscribe(data => {
      swal({
        type: 'success',
        title: 'Thay đổi thông tin sản phẩm ' + this.refrigerator.name + ' thành công!'
      });

      this.router.navigate(['/admin/refrigerations/refrigerators']);
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