import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../shared/services/auth.service';
import { UserService } from '../../../shared/services/user.service';
import { CommonModule } from '@angular/common';

@Component({
    selector: 'app-profile',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './profile.component.html',
    styleUrl: './profile.component.css',
})
export class ProfileComponent implements OnInit {

    user: any = {};
    isLoading: boolean = true;

    constructor(private authService: AuthService, private userService: UserService) { }

    ngOnInit(): void {

        this.fetchUserProfile();
    }

    fetchUserProfile(): void {
        const token = this.authService.getToken();

        if (token) {

            const decodedToken: any = this.authService.decodeToken(token);
            const userId = decodedToken?.userId;

            if (userId) {

                this.userService.getUserById(userId).subscribe({
                    next: (response) => {

                        this.user = response;
                        this.isLoading = false;
                    },
                    error: (error) => {

                        console.error('Error fetching user profile:', error);
                        this.isLoading = false;
                    },
                });
            }
        }
    }
}