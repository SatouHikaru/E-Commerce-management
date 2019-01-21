import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './module/home/home.component';
import { HeaderComponent } from './module/header/header.component';
import { FooterComponent } from './module/footer/footer.component';
import { NavComponent } from './module/nav/nav.component';
import { RegisterComponent } from './module/account/components/customer/components/register/register.component';
import { LoginComponent } from './module/account/components/login/login.component';
import { CustomerDetailComponent } from './module/account/components/customer/components/customer-detail/customer-detail.component';
import { CustomerListComponent } from './module/account/components/customer/components/customer-list/customer-list.component';
import { EmployeeListComponent } from './module/account/components/employee/components/employee-list/employee-list.component';
import { EmployeeFormComponent } from './module/account/components/employee/components/employee-form/employee-form.component';
import { EmployeeDetailComponent } from './module/account/components/employee/components/employee-detail/employee-detail.component';
import { TelevisionListComponent } from './module/product/components/electric/television/components/television-list/television-list.component';
import { TelevisionDetailComponent } from './module/product/components/electric/television/components/television-detail/television-detail.component';
import { DVDPlayerListComponent } from './module/product/components/electric/dvd-player/components/dvd-player-list/dvd-player-list.component';
import { DVDPlayerDetailComponent } from './module/product/components/electric/dvd-player/components/dvd-player-detail/dvd-player-detail.component';
import { WasherListComponent } from './module/product/components/refrigeration/washer/components/washer-list/washer-list.component';
import { WasherDetailComponent } from './module/product/components/refrigeration/washer/components/washer-detail/washer-detail.component';
import { RefrigeratorListComponent } from './module/product/components/refrigeration/refrigerator/components/refrigerator-list/refrigerator-list.component';
import { RefrigeratorDetailComponent } from './module/product/components/refrigeration/refrigerator/components/refrigerator-detail/refrigerator-detail.component';
import { AirConditionerListComponent } from './module/product/components/refrigeration/air-conditioner/components/air-conditioner-list/air-conditioner-list.component';
import { AirConditionerDetailComponent } from './module/product/components/refrigeration/air-conditioner/components/air-conditioner-detail/air-conditioner-detail.component';
import { RiceCookerListComponent } from './module/product/components/appliance/rice-cooker/components/rice-cooker-list/rice-cooker-list.component';
import { RiceCookerDetailComponent } from './module/product/components/appliance/rice-cooker/components/rice-cooker-detail/rice-cooker-detail.component';
import { WirelessRouterListComponent } from './module/product/components/accessory/wireless-router/components/wireless-router-list/wireless-router-list.component';
import { WirelessRouterDetailComponent } from './module/product/components/accessory/wireless-router/components/wireless-router-detail/wireless-router-detail.component';
import { OrderDetailComponent } from './module/order/components/order-detail/order-detail.component';
import { AdminHeaderComponent } from './module/admin/admin-header/admin-header.component';
import { AdminProductNavComponent } from './module/admin/admin-product-nav/admin-product-nav.component';
import { AdminAccountNavComponent } from './module/admin/admin-account-nav/admin-account-nav.component';
import { AdminTelevisionListComponent } from './module/product/components/electric/television/components/admin-television-list/admin-television-list.component';
import { AdminTelevisionDetailComponent } from './module/product/components/electric/television/components/admin-television-detail/admin-television-detail.component';
import { TelevisionFormComponent } from './module/product/components/electric/television/components/television-form/television-form.component';
import { AdminDVDPlayerListComponent } from './module/product/components/electric/dvd-player/components/admin-dvd-player-list/admin-dvd-player-list.component';
import { AdminDVDPlayerDetailComponent } from './module/product/components/electric/dvd-player/components/admin-dvd-player-detail/admin-dvd-player-detail.component';
import { DVDPlayerFormComponent } from './module/product/components/electric/dvd-player/components/dvd-player-form/dvd-player-form.component';
import { AdminWasherListComponent } from './module/product/components/refrigeration/washer/components/admin-washer-list/admin-washer-list.component';
import { AdminWasherDetailComponent } from './module/product/components/refrigeration/washer/components/admin-washer-detail/admin-washer-detail.component';
import { WasherFormComponent } from './module/product/components/refrigeration/washer/components/washer-form/washer-form.component';
import { AdminRefrigeratorListComponent } from './module/product/components/refrigeration/refrigerator/components/admin-refrigerator-list/admin-refrigerator-list.component';
import { AdminRefrigeratorDetailComponent } from './module/product/components/refrigeration/refrigerator/components/admin-refrigerator-detail/admin-refrigerator-detail.component';
import { RefrigeratorFormComponent } from './module/product/components/refrigeration/refrigerator/components/refrigerator-form/refrigerator-form.component';
import { AdminAirConditionerListComponent } from './module/product/components/refrigeration/air-conditioner/components/admin-air-conditioner-list/admin-air-conditioner-list.component';
import { AdminAirConditionerDetailComponent } from './module/product/components/refrigeration/air-conditioner/components/admin-air-conditioner-detail/admin-air-conditioner-detail.component';
import { AirConditionerFormComponent } from './module/product/components/refrigeration/air-conditioner/components/air-conditioner-form/air-conditioner-form.component';
import { AdminRiceCookerListComponent } from './module/product/components/appliance/rice-cooker/components/admin-rice-cooker-list/admin-rice-cooker-list.component';
import { AdminRiceCookerDetailComponent } from './module/product/components/appliance/rice-cooker/components/admin-rice-cooker-detail/admin-rice-cooker-detail.component';
import { RiceCookerFormComponent } from './module/product/components/appliance/rice-cooker/components/rice-cooker-form/rice-cooker-form.component';
import { AdminWirelessRouterListComponent } from './module/product/components/accessory/wireless-router/components/admin-wireless-router-list/admin-wireless-router-list.component';
import { AdminWirelessRouterDetailComponent } from './module/product/components/accessory/wireless-router/components/admin-wireless-router-detail/admin-wireless-router-detail.component';
import { WirelessRouterFormComponent } from './module/product/components/accessory/wireless-router/components/wireless-router-form/wireless-router-form.component';
import { OrderListComponent } from './module/order/components/order-list/order-list.component';
import { AdminOrderDetailComponent } from './module/order/components/admin-order-detail/admin-order-detail.component';
import { StatisticComponent } from './module/statistic/statistic.component';
import { PaginationComponent } from './module/pagination/pagination.component';
import { PageService } from './common/page.service';
import { ErrorComponent } from './module/error/error.component';
import { AuthGuard } from './common/auth/auth.guard';
import { Proxy } from './common/proxy.class';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    NavComponent,
    RegisterComponent,
    LoginComponent,
    CustomerDetailComponent,
    CustomerListComponent,
    EmployeeListComponent,
    EmployeeFormComponent,
    EmployeeDetailComponent,
    TelevisionListComponent,
    TelevisionDetailComponent,
    DVDPlayerListComponent,
    DVDPlayerDetailComponent,
    WasherListComponent,
    WasherDetailComponent,
    RefrigeratorListComponent,
    RefrigeratorDetailComponent,
    AirConditionerListComponent,
    AirConditionerDetailComponent,
    RiceCookerListComponent,
    RiceCookerDetailComponent,
    WirelessRouterListComponent,
    WirelessRouterDetailComponent,
    OrderDetailComponent,
    AdminHeaderComponent,
    AdminProductNavComponent,
    AdminAccountNavComponent,
    AdminTelevisionListComponent,
    AdminTelevisionDetailComponent,
    TelevisionFormComponent,
    AdminDVDPlayerListComponent,
    AdminDVDPlayerDetailComponent,
    DVDPlayerFormComponent,
    AdminWasherListComponent,
    AdminWasherDetailComponent,
    WasherFormComponent,
    AdminRefrigeratorListComponent,
    AdminRefrigeratorDetailComponent,
    RefrigeratorFormComponent,
    AdminAirConditionerListComponent,
    AdminAirConditionerDetailComponent,
    AirConditionerFormComponent,
    AdminRiceCookerListComponent,
    AdminRiceCookerDetailComponent,
    RiceCookerFormComponent,
    AdminWirelessRouterListComponent,
    AdminWirelessRouterDetailComponent,
    WirelessRouterFormComponent,
    OrderListComponent,
    AdminOrderDetailComponent,
    StatisticComponent,
    PaginationComponent,
    ErrorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [PageService, AuthGuard, Proxy],
  bootstrap: [AppComponent]
})
export class AppModule { }
