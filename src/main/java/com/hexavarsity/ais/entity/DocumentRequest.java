package com.hexavarsity.ais.entity;

import jakarta.persistence.*;
import java.util.Date;

// DocumentRequest Entity
@Entity
@Table(name = "DocumentRequest")
public class DocumentRequest {

    /************************************ Attributes ************************************/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @ManyToOne
    @JoinColumn(name = "proposal_id", nullable = false)
    private Proposal proposal;

    @Column(nullable = false, length = 100)
    private String requestType;

    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;

    @Temporal(TemporalType.TIMESTAMP)
    private Date requestDate = new Date();

    
    /************************************ Getters and Setters ************************************/

    // Getters and Setters for requestId
    public Integer getRequestId() {
        return requestId;
    }
    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    // Getters and Setters for proposal
    public Proposal getProposal() {
        return proposal;
    }
    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    // Getters and Setters for requestType
    public String getRequestType() {
        return requestType;
    }
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    // Getters and Setters for status
    public RequestStatus getStatus() {
        return status;
    }
    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    // Getters and Setters for requestDate
    public Date getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
}

// Enum RequestStatus
enum RequestStatus {
    PENDING, COMPLETED
}