import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/login';

  constructor(private http: HttpClient, private router: Router) {}

  login(email: string, password: string): Observable<any> {
    return this.http.post<any>(this.apiUrl, { email, password }).pipe(
      tap((response) => {
        localStorage.setItem('token', response.token);
        const role = this.getUserRole(response.token);
        this.redirectToDashboard(role ?? '');
      })
    );
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getUserRole(token: string): string | null {
    const payload = JSON.parse(atob(token.split('.')[1]));
    return payload.role;
  }

  redirectToDashboard(role: string): void {
    if (role === 'ROLE_USER_EMPLOYE') {
      this.router.navigate(['/dashboard-employe']);
    } else if (role === 'ROLE_USER_CLIENT') {
      this.router.navigate(['/dashboard-client']);
    }else if (role === 'ROLE_ROLE_USER_SUPEREMPLOYE') {
      this.router.navigate(['/dashboard-client']);
    }
    else {
      this.router.navigate(['/login']);
    }
  }

  logout(): void {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }
}
