import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
    selector: 'app-logout',
    standalone: true,
    imports: [],
    templateUrl: './logout.component.html',
    styleUrl: './logout.component.css'
})
export class LogoutComponent {
    constructor(private authService: AuthService, private router: Router) {}

    onLogout(): void {
        this.authService.logout().subscribe({
            next: (response) => {
                alert('Logged out successfully.');
                this.authService.storeToken(''); // Clear token
                this.router.navigate(['/auth/login']); // Redirect to login
            },
            error: (err) => {
                console.error('Logout failed:', err);
                alert('Something went wrong during logout.');
            },
        });
    }
}