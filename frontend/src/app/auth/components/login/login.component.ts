import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../shared/services/auth.service';
import { FormsModule } from '@angular/forms';
import { jwtDecode } from 'jwt-decode';
import { CommonModule } from '@angular/common';


@Component({
    selector: 'app-login',
    standalone: true,
    imports: [FormsModule, CommonModule],
    templateUrl: './login.component.html',
    styleUrl: './login.component.css'
})
export class LoginComponent {

    email: string = '';
    password: string = '';

    isLoading: boolean = false;

    constructor(private authService: AuthService, private router: Router) { }

    onLogin(): void {

        this.isLoading = true;
        this.authService.login(this.email, this.password).subscribe({
            next: (response) => {

                this.isLoading = false;
                this.authService.storeToken(response.token);

                // Decoding token to get user role
                const decodedToken: any = jwtDecode(response.token);
                // Backend is sending role field so I am storing it in role variable
                const role = decodedToken.role;

                if (role === 'ROLE_USER') {

                    this.router.navigate(['/user/dashboard']);
                }
                else if (role === 'ROLE_OFFICER') {

                    this.router.navigate(['/officer/dashboard']);
                }
                else {

                    alert('Unknown role. Please contact support.');
                }
            },
            error: (error) => {

                this.isLoading = false;
                console.error('Login failed:', error);
                alert('Invalid credentials!');
            },
        });
    }

    onCreateAccount(): void {

        this.router.navigate(['/auth/register']);
    }
}