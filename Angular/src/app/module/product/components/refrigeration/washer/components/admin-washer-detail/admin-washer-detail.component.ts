import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { WasherService } from '../../service/washer.service';
import { BrandService } from 'src/app/module/product/service/brand.service';
import { Washer } from '../../model/washer.class';
import { Brand } from 'src/app/module/product/model/brand.class';
import swal from 'sweetalert2';

@Component({
  selector: 'app-admin-washer-detail',
  templateUrl: './admin-washer-detail.component.html',
  styleUrls: ['./admin-washer-detail.component.css']
})
export class AdminWasherDetailComponent implements OnInit {

  washerForm: FormGroup;
  washer: Washer = new Washer()
  brands: Brand[]
  brandID: number
  title: string
  image: string = 'assets/images/notfound.jpg'
  fileUpload: File
  quantity: number = 0

  constructor(private router: Router, private activateRoute: ActivatedRoute, private location: Location,
      private formBuilder: FormBuilder, private washerService: WasherService,
      private brandService: BrandService) { }

  ngOnInit() {
    if (localStorage.getItem('role') == 'customer') {
      this.router.navigate(['']);
    }

    const name = this.activateRoute.snapshot.params['name'];
    this.washerService.getByName(name).subscribe(washer => {
      this.washer = washer;
      this.brandID = this.washer.brandID;
      this.image = this.washer.image;
    });

    this.washerForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
      image: [''],
      price: ['', Validators.required],
      quantity: ['', Validators.required],
      
      type: ['', [Validators.required, Validators.maxLength(20)]],
      laundryCage: ['', [Validators.required, Validators.maxLength(20)]],
      spinSpeed: ['', Validators.required],
      engine: ['', [Validators.required, Validators.maxLength(50)]],
      size: ['', [Validators.required, Validators.maxLength(50)]],
      weight: [''],
      standardTechnology: ['', [Validators.required, Validators.maxLength(20)]],

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
    this.washer.quantity = this.washer.quantity + this.quantity;
    this.washer.brandID = this.washerForm.get('brand').value;
    const formData = new FormData();
    formData.append('washer', JSON.stringify(this.washer));
    if (this.fileUpload != null) {
      formData.append('fileUpload', this.fileUpload);
    }

    this.washerService.update(this.washer.id, formData).subscribe(data => {
      swal({
        type: 'success',
        title: 'Thay đổi thông tin sản phẩm ' + this.washer.name + ' thành công!'
      });

      this.router.navigate(['/admin/refrigerations/washers']);
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