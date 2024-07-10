import { Routes } from '@angular/router';
import {LoginComponent} from "./pages/login/login.component";
import {LayoutComponent} from "./pages/layout/layout.component";
import {DashboardComponent} from "./pages/dashboard/dashboard.component";
import {DoctorConsultsComponent} from "./pages/doctor-consults/doctor-consults.component";

export const routes: Routes =
  [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: '',
    component: LayoutComponent,
    children: [{
      path: 'dashboard',
      component: DashboardComponent
    }]
  }
  ,
    {
      path: '',
      component: LayoutComponent,
      children: [{
        path: 'doctor-consults',
        component: DoctorConsultsComponent
      }]
    }
  ];
