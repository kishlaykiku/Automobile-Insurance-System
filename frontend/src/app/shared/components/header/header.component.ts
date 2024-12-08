import { Component } from '@angular/core';
import { LogoutComponent } from "../logout/logout.component";
import { AuthService } from '../../services/auth.service';
import { NgIf } from '@angular/common';

@Component({
    selector: 'app-header',
    standalone: true,
    imports: [NgIf, LogoutComponent],
    templateUrl: './header.component.html',
    styleUrl: './header.component.css'
})
export class HeaderComponent {

    isLoggedIn: boolean = false;

    constructor(private authService: AuthService) {}

    ngOnInit(): void {
        this.authService.loggedIn$.subscribe((status) => {
            this.isLoggedIn = status;
        });
    }
}