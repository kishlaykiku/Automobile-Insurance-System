import { Component, OnInit } from '@angular/core';
import { ProposalService } from '../../../shared/services/proposal.service';
import * as bootstrap from 'bootstrap';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';


@Component({
    selector: 'app-view-proposals',
    standalone: true,
    imports: [FormsModule, CommonModule],
    templateUrl: './view-proposals.component.html',
    styleUrl: './view-proposals.component.css'
})
export class ViewProposalsComponent {

    proposals: any[] = [];
    filteredProposals: any[] = [];
    selectedProposal: any = null;
    filterCriteria: string = 'all';
    sortCriteria: string = 'proposalId';
    isLoading: boolean = true;

    constructor(private proposalService: ProposalService) { }

    ngOnInit(): void {

        this.fetchProposals();
    }

    fetchProposals(): void {

        this.isLoading = true;
        this.proposalService.getProposals().subscribe({
            next: (data) => {

                this.proposals = data;
                this.filteredProposals = [...this.proposals];
                this.isLoading = false;
            },
            error: (error) => {

                console.error('Error fetching proposals:', error);
                this.isLoading = false;
            },
        });
    }

    applyFilter(): void {

        if (this.filterCriteria === 'all') {

            this.filteredProposals = [...this.proposals];
        }
        else {

            this.filteredProposals = this.proposals.filter(proposal => proposal.status.toLowerCase() === this.filterCriteria);
        }

        this.applySort();
    }

    applySort(): void {

        this.filteredProposals.sort((a, b) => {
            if (this.sortCriteria === 'proposalId') {

                return a.proposalId.localeCompare(b.proposalId);
            }
            else if (this.sortCriteria === 'submissionDate') {

                return new Date(a.submissionDate).getTime() - new Date(b.submissionDate).getTime();
            }

            return 0;
        });
    }

    viewDetails(proposal: any): void {

        this.proposalService.getProposalDetails(proposal.proposalId).subscribe({
            next: (data) => {

                this.selectedProposal = data;
                const modalElement = document.getElementById('proposalDetailsModal');

                if (modalElement) {

                    const modal = new bootstrap.Modal(modalElement);
                    modal.show();
                }
            },
            error: (error) => console.error('Error fetching proposal details:', error),
        });
    }

    deleteProposal(proposalId: string): void {

        if (confirm('Are you sure you want to delete this proposal?')) {

            this.proposalService.deleteProposal(proposalId).subscribe({
                next: () => {

                    this.proposals = this.proposals.filter(p => p.proposalId !== proposalId);
                    this.applyFilter();
                },
                error: (error) => console.error('Error deleting proposal:', error),
            });
        }
    }
}