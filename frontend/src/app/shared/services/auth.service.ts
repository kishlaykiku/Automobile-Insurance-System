import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject, tap } from 'rxjs';
import { environment } from '../../../environments/environment';
import { jwtDecode } from 'jwt-decode';


@Injectable({
    providedIn: 'root',
})
export class AuthService {

    private apiUrl = environment.apiBaseUrl; // 'http://localhost:3000/api';

    private loggedInSubject = new BehaviorSubject<boolean>(this.isLoggedIn());
    public loggedIn$ = this.loggedInSubject.asObservable();

    constructor(private http: HttpClient) {}

    login(email: string, password: string): Observable<any> {

        return this.http.post(`${this.apiUrl}/auth/login`, { username: email, password }).pipe(
        tap((response: any) => {

            this.storeToken(response.token);
        })
    );
    }

    isLoggedIn(): boolean {

        if (typeof window === 'undefined') {

            return false; // Return false when running on the server
        }
        return !!localStorage.getItem('token');
    }

    register(userData: any): Observable<any> {

        return this.http.post(`${this.apiUrl}/users/create`, userData);
    }

    storeToken(token: string): void {

        localStorage.setItem('auth_token', token); // Storing token in local storage
        this.loggedInSubject.next(!!token); // Updating login state
    }

    getToken(): string | null {

        return localStorage.getItem('auth_token');
    }

    getRole(): string | null {

        const token = this.getToken();
        if (!token) return null;
    
        const decodedToken: any = jwtDecode(token);
        return decodedToken.role || null;
    }

    isAuthenticated(): boolean {

        return !!this.getToken();
    }

    logout(): Observable<string> {

        localStorage.removeItem('auth_token'); // Removing token from local storage
        this.loggedInSubject.next(false); // Updating login state
        return this.http.post(`${this.apiUrl}/auth/logout`, {}, { responseType: 'text' });
    }
}