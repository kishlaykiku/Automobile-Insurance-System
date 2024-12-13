import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { HeaderComponent } from "./shared/components/header/header.component";
import { AuthService } from './shared/services/auth.service';


@Component({
    selector: 'app-root',
    standalone: true,
    imports: [RouterOutlet, HeaderComponent],
    templateUrl: './app.component.html',
    styleUrl: './app.component.css'
})
export class AppComponent {

    title = 'frontend';

    constructor(private authService: AuthService, private router: Router) {}

    onLogout(): void {

        this.authService.logout().subscribe({
            next: () => {

                alert('Logged out successfully.');
                this.router.navigate(['/auth/login']);
            },
            error: () => {

                alert('Something went wrong during logout.');
            },
        });
    }
}