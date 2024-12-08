import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../shared/services/auth.service';
import { FormsModule } from '@angular/forms';

@Component({
    selector: 'app-register',
    standalone: true,
    imports: [FormsModule],
    templateUrl: './register.component.html',
    styleUrl: './register.component.css'
})
export class RegisterComponent {
    name: string = '';
    email: string = '';
    password: string = '';
    address: string = '';
    dob: string = '';
    aadharNo: string = '';
    panNo: string = '';

    constructor(private authService: AuthService, private router: Router) {}

    onRegister(): void {
        const userData = {
            name: this.name,
            email: this.email,
            password: this.password,
            address: this.address,
            dob: this.dob,
            aadharNo: this.aadharNo,
            panNo: this.panNo,
        };

        this.authService.register(userData).subscribe({
            next: () => {
                alert('Registration successful! Please login.');
                this.router.navigate(['/auth/login']);
            },
            error: (error) => {
                console.error('Registration failed:', error);
                alert('Registration failed. Please try again.');
            },
        });
    }

    onNavigateToLogin(): void {
        this.router.navigate(['/auth/login']);
    }
}