import {NgModule} from  '@angular/core';
import {Routes, RouterModule} from  '@angular/router';
import {EmployeeDashboardComponent} from './employee-dashboard/employee-dashboard.component';
import {UsersComponent} from './employee-dashboard/users/users.component';
import {OperationsComponent} from './employee-dashboard/operations/operations.component';
import {GroupsComponent} from './employee-dashboard/groups/groups.component';
import {DashboardComponent} from './employee-dashboard/dashboard/dashboard.component';
import {LoginComponent} from './login/login.component';
import {SupemployeDashboardComponent} from './supemploye-dashboard/supemploye-dashboard.component';
import {Groups2Component} from './supemploye-dashboard/groups/groups.component';
import {EmployesComponent} from './supemploye-dashboard/employes/employes.component';


export const routes: Routes = [
  {path: 'employee', component: EmployeeDashboardComponent,
    children : [
    {path: 'dashboards', component: DashboardComponent},
    {path: 'clients', component: UsersComponent},
      {path: 'groups', component: GroupsComponent},
    {path:'operations', component: OperationsComponent},
      {path:'logout', redirectTo:"/login"}
    ],
  },
  {path: 'Supemployee', component: SupemployeDashboardComponent,
    children : [
      {path: 'employes', component: EmployesComponent},
      {path: 'groups', component: Groups2Component},
      {path:'logout', redirectTo:"/login"}
    ],
  },
  {path: 'login', component: LoginComponent},
  {path: '', redirectTo: '/employee/dashboards', pathMatch: 'full'},
  {path: '**', redirectTo: '/employee/dashboards'}
  ];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export  class  AppRoutingModule { }
