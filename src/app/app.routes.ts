import {NgModule} from  '@angular/core';
import {Routes, RouterModule} from  '@angular/router';
import {EmployeeDashboardComponent} from './employee-dashboard/employee-dashboard.component';
import {UsersComponent} from './employee-dashboard/users/users.component';
import {OperationsComponent} from './employee-dashboard/operations/operations.component';
import {GroupsComponent} from './employee-dashboard/groups/groups.component';
import {DashboardComponent} from './employee-dashboard/dashboard/dashboard.component';
import {LoginComponent} from './login/login.component';

export const routes: Routes = [
  {path: 'employee', component: EmployeeDashboardComponent,
    children : [
    {path: 'dashboards', component: DashboardComponent},
    {path: 'clients', component: UsersComponent},
      {path: 'groups', component: GroupsComponent},
    {path:'operations', component: OperationsComponent}
    ],
  },
  {path: 'login', component: LoginComponent},
  //other routes
  {path: '', redirectTo: '/employee/dashboards', pathMatch: 'full'},
  {path: '**', redirectTo: '/employee/dashboards'} //Wildcards route for unmatched routes
  ];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export  class  AppRoutingModule { }
