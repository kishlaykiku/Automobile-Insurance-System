import { Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ViewPoliciesComponent } from './components/view-policies/view-policies.component';
import { ViewProposalsComponent } from './components/view-proposals/view-proposals.component';


export const USER_ROUTES: Routes = [

    { path: 'dashboard', component: DashboardComponent },
    { path: 'profile', component: ProfileComponent },
    { path: 'policies', component: ViewPoliciesComponent },
    { path: 'proposals', component: ViewProposalsComponent },
];