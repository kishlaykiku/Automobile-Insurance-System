import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../shared/services/auth.service';
import { FormsModule } from '@angular/forms';
import { jwtDecode } from 'jwt-decode';

@Component({
    selector: 'app-login',
    standalone: true,
    imports: [FormsModule],
    templateUrl: './login.component.html',
    styleUrl: './login.component.css'
})
export class LoginComponent {
    email: string = '';
    password: string = '';

    constructor(private authService: AuthService, private router: Router) {}

    onLogin(): void {
        this.authService.login(this.email, this.password).subscribe({
            next: (response) => {
                this.authService.storeToken(response.token);

                // Decode token to get user role
                const decodedToken: any = jwtDecode(response.token);
                const role = decodedToken.role; // Ensure the backend sends a 'role' field in the JWT payload

                if (role === 'ROLE_USER') {
                    this.router.navigate(['/user/dashboard']);
                } else if (role === 'ROLE_OFFICER') {
                    this.router.navigate(['/officer/dashboard']);
                } else {
                    alert('Unknown role. Please contact support.');
                }
            },
            error: (error) => {
                console.error('Login failed:', error);
                alert('Invalid credentials!');
            },
        });
    }

    onCreateAccount(): void {
        this.router.navigate(['/auth/register']);
    }
}