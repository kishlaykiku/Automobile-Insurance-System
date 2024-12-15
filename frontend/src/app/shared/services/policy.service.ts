import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { AuthService } from './auth.service';

@Injectable({
    providedIn: 'root',
})
export class PolicyService {

    private apiUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient, private authService: AuthService) { }

    getPolicies(): Observable<any> {

        const token = this.authService.getToken();
        const headers = new HttpHeaders({
            Authorization: `Bearer ${token}`,
        });

        return this.http.get(`${this.apiUrl}/policies/getall`, { headers });
    }
}