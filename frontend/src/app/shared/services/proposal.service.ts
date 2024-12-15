import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { environment } from '../../../environments/environment';
import { AuthService } from './auth.service';


@Injectable({
    providedIn: 'root',
})
export class ProposalService {

    private apiUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient, private authService: AuthService) { }

    getProposals(): Observable<any> {

        const token = this.authService.getToken();
        if (!token) {

            return throwError(() => new Error('No token found'));
        }

        const decodedToken: any = this.authService.decodeToken(token);
        if (!decodedToken || !decodedToken.userId) {

            return throwError(() => new Error('Invalid token or missing user ID'));
        }

        const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

        return this.http.get(`${this.apiUrl}/proposals/get/proposal-by-user/${decodedToken.userId}`, { headers });
    }

    getProposalDetails(proposalId: string): Observable<any> {

        const token = this.authService.getToken();
        if (!token) {
            return throwError(() => new Error('No token found'));
        }

        const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

        return this.http.get(`${this.apiUrl}/proposals/get/${proposalId}`, { headers });
    }

    deleteProposal(proposalId: string): Observable<any> {

        const token = this.authService.getToken();
        if (!token) {

            return throwError(() => new Error('No token found'));
        }

        const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

        return this.http.delete(`${this.apiUrl}/proposals/delete/${proposalId}`, { headers });
    }
}