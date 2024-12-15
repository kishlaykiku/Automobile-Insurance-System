import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import * as bootstrap from 'bootstrap';
import { PolicyService } from '../../../shared/services/policy.service';


@Component({
    selector: 'app-view-policies',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './view-policies.component.html',
    styleUrl: './view-policies.component.css',
})
export class ViewPoliciesComponent implements OnInit {

    policies: any[] = [];

    filterCriteria: string = 'all';
    filteredPolicies = [...this.policies];
    sortCriteria: string = 'default';
    selectedPolicy: any = null;
    isLoading: boolean = true;

    constructor(private policyService: PolicyService) { }

    ngOnInit(): void {

        this.fetchPolicies();
        this.applyFilter();
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
                this.isLoading = false
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

                if (this.sortCriteria === 'policyNo') {

                    return a.name.localeCompare(b.name);
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