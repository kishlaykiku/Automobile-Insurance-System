import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../shared/services/auth.service';
import { Router } from '@angular/router';


@Component({
    selector: 'app-dashboard',
    standalone: true,
    imports: [],
    templateUrl: './dashboard.component.html',
    styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {

    userName: string = 'User';

    constructor(private authService: AuthService, private router: Router) { }

    ngOnInit(): void {

        // Fetch user's name from token
        const token = this.authService.getToken();

        if (token) {

            const decodedToken: any = this.authService.decodeToken(token);
            this.userName = decodedToken?.name || 'User';
        }
    }

    onViewPolicies(): void {

        this.router.navigate(['/user/policies']);
        console.log('View Policies clicked');
    }

    onViewProposals(): void {

        this.router.navigate(['/user/proposals']);
        console.log('View Proposals clicked');
    }
}