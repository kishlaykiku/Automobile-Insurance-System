import { Routes } from '@angular/router';
import { authGuard } from './shared/guards/auth.guard';


export const routes: Routes = [

    {
        path: '',
        redirectTo: '/auth/login',
        pathMatch: 'full',
    },
    {
        path: 'auth',
        loadChildren: () => import('./auth/auth.routes').then((m) => m.AUTH_ROUTES),
    },
    {
        path: 'user',
        loadChildren: () => import('./user/user.routes').then((m) => m.USER_ROUTES),
        canActivate: [authGuard],
        data: { role: 'ROLE_USER' },
    },
    {
        path: 'officer',
        loadChildren: () => import('./officer/officer.routes').then((m) => m.OFFICER_ROUTES),
        canActivate: [authGuard],
        data: { role: 'ROLE_OFFICER' },
    },
    {
        path: '**',
        redirectTo: '/auth/login',
    },
];