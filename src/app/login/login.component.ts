import { Component } from '@angular/core';
import { AuthService } from '../services/authentication.service';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './login.component.html'
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor(private authService: AuthService) {}

  onLogin() {
    this.authService.login(this.email, this.password).subscribe({
      next: () => console.log("Successfully logged in"),
      error: (err) => console.error('Login failed', err)
    });
  }
}
