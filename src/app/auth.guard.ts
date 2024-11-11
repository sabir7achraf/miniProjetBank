import { Injectable, inject } from '@angular/core';
import { CanActivateFn, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from './services/authentication.service';

export const authGuard: CanActivateFn = (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
  const authService = inject(AuthService);
  const router = inject(Router);
  const token = authService.getToken();

  if (token) {
    const role = authService.getUserRole(token);

    // Vérifie si le rôle correspond à la route demandée
    if ((role === 'Employe' && state.url === '/dashboard-employe') ||
      (role === 'Admin' && state.url === '/dashboard-admin')) {
      return true;
    } else {
      authService.redirectToDashboard(role ?? '');  // Redirige vers le bon dashboard
      return false;
    }
  }

  // Redirige vers la page de connexion si l'utilisateur n'est pas authentifié
  router.navigate(['/login']);
  return false;
};
