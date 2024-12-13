import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';


export const authGuard: CanActivateFn = (route, state) => {

    const authService = inject(AuthService);
    const router = inject(Router);

    // Get the token and role from AuthService
    const token = authService.getToken();
    const userRole = authService.getRole();
    const requiredRole = route.data['role'];

    // If there's no token, redirect to login
    if (!token) {
        router.navigate(['/auth/login']);
        return false;
    }

    // Check if the role matches the required role for the route
    if (requiredRole && userRole !== requiredRole) {
        router.navigate(['/auth/login']);
        return false;
    }

    // Allow navigation if all checks pass
    return true;
};