import { Component } from '@angular/core';
import { LogoutComponent } from "../logout/logout.component";
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';


@Component({
    selector: 'app-header',
    standalone: true,
    imports: [CommonModule, LogoutComponent, RouterModule],
    templateUrl: './header.component.html',
    styleUrl: './header.component.css'
})
export class HeaderComponent {

    isAdmin: boolean = false;
    dashboardLink: string = '/user/dashboard';
    profileLink: string = '/user/profile';
    isLoggedIn: boolean = false;

    constructor(private authService: AuthService) {}

    ngOnInit(): void {

        this.authService.loggedIn$.subscribe((status) => {
            this.isLoggedIn = status;
        });

        const token = this.authService.getToken();
        if (token) {

            const decodedToken = this.authService.decodeToken(token);
            this.isAdmin = decodedToken?.role === 'ROLE_OFFICER';

            if (this.isAdmin) {

                this.dashboardLink = '/officer/dashboard';
                this.profileLink = '/officer/profile';
            }
        }
    }
}