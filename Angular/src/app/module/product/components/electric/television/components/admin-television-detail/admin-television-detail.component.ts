import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { TelevisionService } from '../../service/television.service';
import { BrandService } from 'src/app/module/product/service/brand.service';
import { Television } from '../../model/television.class';
import { Brand } from 'src/app/module/product/model/brand.class';
import swal from 'sweetalert2';

@Component({
  selector: 'app-admin-television-detail',
  templateUrl: './admin-television-detail.component.html',
  styleUrls: ['./admin-television-detail.component.css']
})
export class AdminTelevisionDetailComponent implements OnInit {

  televisionForm: FormGroup
  television: Television = new Television
  brands: Brand[]
  brandID: number
  image = ''
  fileUpload: File = null
  quantity: number = 0

  constructor(private router: Router, private activateRoute: ActivatedRoute, private location: Location,
      private formBuilder: FormBuilder, private televisionService: TelevisionService,
      private brandService: BrandService) { }

  ngOnInit() {
    if (localStorage.getItem('role') == 'customer') {
      this.router.navigate(['']);
    }

    const name = this.activateRoute.snapshot.params['name'];
    this.televisionService.getByName(name).subscribe(television => {
      this.television = television;
      this.brandID = this.television.brandID;
      this.image = this.television.image;
    }, error => {
      console.log(error);
    });

    this.televisionForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
      image: [''],
      price: ['', Validators.required],
      quantity: ['', Validators.required],

      screenSize: ['', [Validators.required, Validators.maxLength(10)]],
      screenResolution: ['', [Validators.required, Validators.maxLength(40)]],
      imageQuality: [''],

      brand: [''],
      description: [''],
      countryOfOrigin: ['']
    });

    this.getBrandList();
  }

  /**
   * Get list of television brand
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
   * Update television
   * 
   * @since 15/12/2018
   */
  update() {
    this.television.quantity = this.television.quantity + this.quantity;
    this.television.brandID = this.televisionForm.get('brand').value;
    const formData = new FormData();
    formData.append('television', JSON.stringify(this.television));
    if (this.fileUpload != null) {
      formData.append('fileUpload', this.fileUpload);
    }
    
    this.televisionService.update(this.television.id, formData).subscribe(data => {
      swal({
        type: 'success',
        title: 'Thay đổi thông tin sản phẩm ' + this.television.name + ' thành công!'
      });

      this.router.navigate(['/admin/electrics/televisions']);
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