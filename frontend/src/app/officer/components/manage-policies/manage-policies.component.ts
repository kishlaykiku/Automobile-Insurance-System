import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import * as bootstrap from 'bootstrap';
import { PolicyService } from '../../../shared/services/policy.service';


@Component({
    selector: 'app-manage-policies',
    standalone: true,
    imports: [FormsModule, RouterModule, CommonModule],
    templateUrl: './manage-policies.component.html',
    styleUrl: './manage-policies.component.css'
})
export class ManagePoliciesComponent implements OnInit {

    policies: any[] = [];
    filterCriteria: string = 'all';
    filteredPolicies: any[] = [];
    sortCriteria: string = 'default';
    selectedPolicy: any = null;
    isLoading: boolean = true;

    constructor(private policyService: PolicyService) { }

    ngOnInit(): void {

        this.fetchPolicies();
        this.applySort();
    }

    fetchPolicies(): void {

        this.isLoading = true;

        this.policyService.getPolicies().subscribe({
            next: (data) => {

                this.policies = data;
                this.filteredPolicies = [...this.policies];
                this.applyFilter();
                this.isLoading = false;
            },
            error: (error) => {

                console.error('Error fetching policies:', error);
                this.isLoading = false;
            }
        });
    }

    applyFilter(): void {

        if (this.filterCriteria === 'all') {

            this.filteredPolicies = [...this.policies];
        }
        else {

            this.filteredPolicies = this.policies.filter((policy) =>
                policy.status.toLowerCase().includes(this.filterCriteria.toLowerCase())
            );
        }

        this.applySort();
    }

    applySort(): void {

        if (this.sortCriteria === 'default') {

            this.filteredPolicies = [...this.filteredPolicies];
        }
        else {

            this.filteredPolicies.sort((a, b) => {

                if (this.sortCriteria === 'policyName') {

                    return a.policyName.localeCompare(b.policyName);
                }
                else if (this.sortCriteria === 'startDate') {

                    return new Date(a.startDate).getTime() - new Date(b.startDate).getTime();
                }
                else if (this.sortCriteria === 'endDate') {

                    return new Date(a.endDate).getTime() - new Date(b.endDate).getTime();
                }
                return 0;
            });
        }
    }

    viewDetails(policy: any): void {

        this.selectedPolicy = policy;

        const modalElement = document.getElementById('policyDetailsModal');
        if (modalElement) {

            const modal = new bootstrap.Modal(modalElement);
            modal.show();
        }
    }
}