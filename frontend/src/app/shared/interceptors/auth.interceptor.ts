import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';

export const AuthInterceptor: HttpInterceptorFn = (req, next) => {
    const authService = inject(AuthService);
    const token = authService.getToken();

    // List of public endpoints to exclude from attaching the token
    const excludedEndpoints = ['/auth/login', '/auth/register', '/auth/logout'];

    // Check if the current request URL matches any excluded endpoints
    const isExcluded = excludedEndpoints.some((endpoint) =>
        req.url.includes(endpoint)
    );

    const authReq = !isExcluded && token
        ? req.clone({
            setHeaders: { Authorization: `Bearer ${token}` },
        })
        : req;

    return next(authReq);
};