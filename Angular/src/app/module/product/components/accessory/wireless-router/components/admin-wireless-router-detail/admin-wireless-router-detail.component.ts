import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { WirelessRouterService } from '../../service/wireless.router.service';
import { BrandService } from 'src/app/module/product/service/brand.service';
import { WirelessRouter } from '../../model/wireless.router.class';
import { Brand } from 'src/app/module/product/model/brand.class';
import swal from 'sweetalert2';

@Component({
  selector: 'app-admin-wireless-router-detail',
  templateUrl: './admin-wireless-router-detail.component.html',
  styleUrls: ['./admin-wireless-router-detail.component.css']
})
export class AdminWirelessRouterDetailComponent implements OnInit {

  wirelessRouterForm: FormGroup;
  wirelessRouter: WirelessRouter = new WirelessRouter()
  brands: Brand[];
  brandID: number
  title: string
  image: string = 'assets/images/notfound.jpg'
  fileUpload: File
  quantity: number = 0

  constructor(private router: Router, private activateRoute: ActivatedRoute, private location: Location,
      private formBuilder: FormBuilder, private wirelessRouterService: WirelessRouterService,
      private brandService: BrandService) { }

  ngOnInit() {
    if (localStorage.getItem('role') == 'customer') {
      this.router.navigate(['']);
    }

    const name = this.activateRoute.snapshot.params['name'];
    this.wirelessRouterService.getByName(name).subscribe(wirelessRouter => {
      this.wirelessRouter = wirelessRouter;
      this.brandID = this.wirelessRouter.brandID;
      this.image = this.wirelessRouter.image;
    });

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
   * Update DVD player
   * @since 15/12/2018
   */
  update() {
    this.wirelessRouter.quantity = this.wirelessRouter.quantity + this.quantity;
    this.wirelessRouter.brandID = this.wirelessRouterForm.get('brand').value;
    const formData = new FormData();
    formData.append('wirelessRouter', JSON.stringify(this.wirelessRouter));
    if (this.fileUpload != null) {
      formData.append('fileUpload', this.fileUpload);
    }

    this.wirelessRouterService.update(this.wirelessRouter.id, formData).subscribe(data => {
      swal({
        type: 'success',
        title: 'Thay đổi thông tin sản phẩm ' + this.wirelessRouter.name + ' thành công!'
      });

      this.router.navigate(['/admin/accessories/wireless-routers']);
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