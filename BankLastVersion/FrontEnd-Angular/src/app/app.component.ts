import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {EmployeeDashboardComponent} from './employee-dashboard/employee-dashboard.component';
import {UsersComponent} from './users/users.component';
import {HttpClientModule} from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, EmployeeDashboardComponent, UsersComponent,HttpClientModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'BANKING APP';
}
