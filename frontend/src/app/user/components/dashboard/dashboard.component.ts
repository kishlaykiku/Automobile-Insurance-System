import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../shared/services/auth.service';
import { ProposalService } from '../../../shared/services/proposal.service';
import { PolicyService } from '../../../shared/services/policy.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import * as bootstrap from 'bootstrap';
import { CommonModule } from '@angular/common';
import { Chart, registerables } from 'chart.js';


@Component({
    selector: 'app-dashboard',
    standalone: true,
    imports: [FormsModule, CommonModule],
    templateUrl: './dashboard.component.html',
    styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {

    userName: string = 'User';
    proposal: any = {};
    vehicle: any = {};
    policies: any[] = [];
    isLoadingPolicies: boolean = true;

    // Simulated data
    totalUsers: number = 10;
    totalPolicies: number = 5;
    totalProposals: number = 2;

    constructor(private authService: AuthService, private router: Router, private proposalService: ProposalService, private policyService: PolicyService) {

        Chart.register(...registerables);
    }

    ngOnInit(): void {

        // Fetch user's name from token
        const token = this.authService.getToken();

        if (token) {

            const decodedToken: any = this.authService.decodeToken(token);
            this.userName = decodedToken?.name || 'User';
            this.proposal.userId = decodedToken?.userId;
        }

        this.proposal.officerId = 'b43a7c7b-3a7d-4f8f-8b7e-3427c51e4d16';
    }

    ngAfterViewInit(): void {

        this.initializeChart();
    }

    initializeChart(): void {

        const ctx = document.getElementById('dashboardPieChart') as HTMLCanvasElement;

        if (ctx) {

            new Chart(ctx, {
                type: 'pie',
                data: {
                    labels: ['Total Users', 'Total Policies', 'Total Proposals'],
                    datasets: [{
                        label: 'Dashboard Stats',
                        data: [this.totalUsers, this.totalPolicies, this.totalProposals],
                        backgroundColor: [
                            'rgba(255, 236, 67, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(206, 99, 255, 1)'
                        ],
                        borderColor: [
                            'rgba(255, 236, 67, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(206, 99, 255, 1)'
                        ],
                        borderWidth: 1
                    }]
                },
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            position: 'top',
                        }
                    }
                }
            });
        }
    }

    onViewPolicies(): void {

        this.router.navigate(['/user/policies']);
        console.log('View Policies clicked');
    }

    onViewProposals(): void {

        this.router.navigate(['/user/proposals']);
        console.log('View Proposals clicked');
    }

    onQuickActionSelect(event: Event): void {

        const selectElement = event.target as HTMLSelectElement;
        const action = selectElement.value;

        if (action === 'submitProposal') {

            this.openSubmitProposalModal();
        }
        else if (action === 'viewNotifications') {

            console.log('View Notifications selected');
        }

        selectElement.value = '';
    }

    openSubmitProposalModal(): void {

        const modalElement = document.getElementById('submitProposalModal');
        if (modalElement) {

            const modal = new bootstrap.Modal(modalElement);
            modal.show();
        }

        this.fetchPolicies();
    }

    fetchPolicies(): void {

        this.policyService.getPolicies().subscribe({
            next: (response) => {

                this.policies = response;
                this.isLoadingPolicies = false;
            },
            error: (error) => {

                console.error('Error fetching policies:', error);
                this.isLoadingPolicies = false;
            }
        });
    }

    submitProposal(): void {

        const user = { userId: this.proposal.userId };

        if (!this.proposal.policyId) {
            alert('Please select a policy before submitting.');
            return;
        }

        const proposalData = {
            ...this.proposal,
            user: user,
            vehicle: this.vehicle,
            policy: { policyId: this.proposal.policyId },
            officer: { officerId: this.proposal.officerId },
            additionalDocs: this.proposal.additionalDocs.join(', ')
        };

        console.log('Proposal Data:', proposalData);

        this.proposalService.submitProposal(proposalData).subscribe({
            next: (response) => {

                alert('Proposal submitted successfully!');
                this.proposal = {};
                this.vehicle = {};
            },
            error: (error) => {

                console.error('Error submitting proposal:', error);
                alert('Failed to submit proposal. Please try again.');
            },
        });
    }
}