import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OfficerService } from '../../../shared/services/officer.service';

@Component({
    selector: 'app-profile',
    standalone: true,
    imports: [CommonModule],
    templateUrl: './profile.component.html',
    styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit {

    admin: any = {};
    isLoading: boolean = true;

    constructor(private officerService: OfficerService) {}

    ngOnInit(): void {

        this.fetchAdminProfile();
    }

    fetchAdminProfile(): void {

        this.officerService.getAdminDetails().subscribe({
            next: (response) => {

                this.admin = response;
                this.isLoading = false;
            },
            error: (error) => {

                console.error('Error fetching admin profile:', error);
                this.isLoading = false;
            },
        });
    }
}