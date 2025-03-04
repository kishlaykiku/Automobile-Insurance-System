import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({
    providedIn: 'root',
})
export class UserService {

    private apiUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient) {}

    getUserById(userId: string): Observable<any> {

        return this.http.get(`${this.apiUrl}/users/get/${userId}`);
    }
}