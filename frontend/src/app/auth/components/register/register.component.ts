import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../shared/services/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';


@Component({
    selector: 'app-register',
    standalone: true,
    imports: [FormsModule, CommonModule],
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

    isLoading: boolean = false;
    confirmPassword: string = '';

    constructor(private authService: AuthService, private router: Router) { }

    onRegister(): void {

        this.isLoading = true;

        if (this.password !== this.confirmPassword) {

            alert('Passwords do not match!');
            return;
        }

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

                this.isLoading = false;
                alert('Registration successful! Please login.');
                this.router.navigate(['/auth/login']);
            },
            error: (error) => {

                this.isLoading = false;
                console.error('Registration failed:', error);
                alert('Registration failed. Please try again.');
            },
        });
    }

    onNavigateToLogin(): void {

        this.router.navigate(['/auth/login']);
    }

    clearFields(registerForm?: any): void {

        this.name = '';
        this.email = '';
        this.password = '';
        this.confirmPassword = '';
        this.address = '';
        this.dob = '';
        this.aadharNo = '';
        this.panNo = '';
    
        if (registerForm) {

            registerForm.resetForm();
        }
    }
}