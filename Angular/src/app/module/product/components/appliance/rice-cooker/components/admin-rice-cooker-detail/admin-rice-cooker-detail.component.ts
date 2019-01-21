import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { RiceCookerService } from '../../service/rice.cooker.service';
import { BrandService } from 'src/app/module/product/service/brand.service';
import { RiceCooker } from '../../model/rice.cooker.class';
import { Brand } from 'src/app/module/product/model/brand.class';
import swal from 'sweetalert2';

@Component({
  selector: 'app-admin-rice-cooker-detail',
  templateUrl: './admin-rice-cooker-detail.component.html',
  styleUrls: ['./admin-rice-cooker-detail.component.css']
})
export class AdminRiceCookerDetailComponent implements OnInit {

  riceCookerForm: FormGroup;
  riceCooker: RiceCooker = new RiceCooker()
  brands: Brand[]
  brandID: number
  title: string;
  image: string = 'assets/images/notfound.jpg'
  fileUpload: File
  quantity: number = 0

  constructor(private router: Router, private activateRoute: ActivatedRoute, private location: Location,
      private formBuilder: FormBuilder, private riceCookerService: RiceCookerService,
      private brandService: BrandService) { }

  ngOnInit() {
    if (localStorage.getItem('role') == 'customer') {
      this.router.navigate(['']);
    }

    const name = this.activateRoute.snapshot.params['name'];
    this.riceCookerService.getByName(name).subscribe(riceCooker => {
      this.riceCooker = riceCooker;
      this.brandID = this.riceCooker.brandID;
      this.image = this.riceCooker.image;
    });

    this.riceCookerForm = this.formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]],
      image: [''],
      price: ['', Validators.required],
      quantity: ['', Validators.required],

      volumetric: ['', Validators.required],
      timer: ['', Validators.required],
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
    this.riceCooker.quantity = this.riceCooker.quantity + this.quantity;
    this.riceCooker.brandID = this.riceCookerForm.get('brand').value;
    const formData = new FormData();
    formData.append('riceCooker', JSON.stringify(this.riceCooker));
    if (this.fileUpload != null) {
      formData.append('fileUpload', this.fileUpload);
    }

    this.riceCookerService.update(this.riceCooker.id, formData).subscribe(data => {
      swal({
        type: 'success',
        title: 'Thay đổi thông tin sản phẩm ' + this.riceCooker.name + ' thành công!'
      });

      this.router.navigate(['/admin/electrics/dvd-players']);
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