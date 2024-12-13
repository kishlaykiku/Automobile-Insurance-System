import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';


@Component({
    selector: 'app-logout',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './logout.component.html',
    styleUrl: './logout.component.css'
})
export class LogoutComponent {

    isLoading: boolean = false;

    constructor(private authService: AuthService, private router: Router) { }

    onLogout(): void {

        this.isLoading = true;
        this.authService.logout().subscribe({
            next: (response) => {

                this.isLoading = false;
                alert('Logged out successfully.');
                this.authService.storeToken(''); // Clearing token [Is Must]
                this.router.navigate(['/auth/login']); // Redirecting to login component
            },
            error: (err) => {

                this.isLoading = false;
                console.error('Logout failed:', err);
                alert('Something went wrong during logout.');
            },
        });
    }
}