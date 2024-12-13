import { Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ManagePoliciesComponent } from './components/manage-policies/manage-policies.component';


export const OFFICER_ROUTES: Routes = [

    { path: 'dashboard', component: DashboardComponent },
    { path: 'manage-policies', component: ManagePoliciesComponent },
];