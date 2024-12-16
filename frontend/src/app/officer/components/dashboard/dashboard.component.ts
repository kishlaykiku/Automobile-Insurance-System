import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import * as bootstrap from 'bootstrap';
import { PolicyService } from '../../../shared/services/policy.service';


@Component({
    selector: 'app-officer-dashboard',
    standalone: true,
    imports: [CommonModule, FormsModule],
    templateUrl: './dashboard.component.html',
    styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

    policy: any = {};

    policyTypes = [
        'Third-Party Liability Insurance',
        'Own Damage Cover',
        'Collision Insurance',
        'Personal Accident Cover',
        'Personal Injury Protection',
        'Comprehensive'
    ];
    addOns = [
        'Engine Protection',
        'Daily Allowance Cover',
        'Consumables Cover',
        'Roadside Assistance Cover',
        'Zero Depreciation Cover',
        'Repair of Accessories'
    ];

    constructor(private router: Router, private policyService: PolicyService) { }

    onQuickActionSelect(event: Event): void {

        const selectElement = event.target as HTMLSelectElement;
        const action = selectElement.value;

        if (action === 'createPolicy') {

            this.openCreatePolicyModal();
        }
        else if (action === 'viewNotifications') {

            console.log('View Notifications selected');
        }

        selectElement.value = '';
    }

    openCreatePolicyModal(): void {

        const modalElement = document.getElementById('createPolicyModal');
        if (modalElement) {

            const modal = new bootstrap.Modal(modalElement);
            modal.show();
        }
    }

    createPolicy(): void {

        if (!this.policy) {

            alert('Please fill in all required fields.');
            return;
        }

        const startDate = new Date(this.policy.startDate);
        const renewalDate = new Date(startDate);
        renewalDate.setMonth(startDate.getMonth() + 6);
        this.policy.renewalDate = renewalDate.toISOString().split('T')[0];

        const policyPayload = {
            ...this.policy,
            addOns: this.policy.addOns ? this.policy.addOns.join(', ') : null,
        };

        this.policyService.createPolicy(policyPayload).subscribe({
            next: (response) => {

                console.log('Policy created successfully:', response);
                alert('Policy created successfully!');
                this.policy = { addOns: [], features: [] };

                const modalElement = document.getElementById('createPolicyModal');
                if (modalElement) {

                    const modal = bootstrap.Modal.getInstance(modalElement);
                    modal?.hide();
                }
            },
            error: (error) => {

                console.error('Error creating policy:', error);
                alert('Failed to create policy. Please try again.');
            },
        });
    }

    onViewPolicies(): void {

        this.router.navigate(['/officer/manage-policies']);
    }

    onReviewProposals(): void {

        console.log('Review Proposals selected');
    }
}