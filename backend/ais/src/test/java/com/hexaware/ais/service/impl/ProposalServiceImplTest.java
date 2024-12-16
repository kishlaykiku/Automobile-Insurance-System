package com.hexaware.ais.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.hexaware.ais.dto.ProposalDTO;
import com.hexaware.ais.entity.*;
import com.hexaware.ais.exception.ResourceNotFoundException;
import com.hexaware.ais.repository.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class ProposalServiceImplTest {

    @InjectMocks
    private ProposalServiceImpl proposalService;

    @Mock
    private ProposalRepository proposalRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private OfficerRepository officerRepository;

    @Mock
    private VehicleRepository vehicleRepository;

    private Proposal proposal;
    private User user;
    private Officer officer;
    private Vehicle vehicle;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        // Initialize User
        user = new User();
        user.setUserId("user123");
        user.setName("John Doe");

        // Initialize Officer
        officer = new Officer();
        officer.setOfficerId("officer123");
        officer.setName("Jane Smith");

        // Initialize Vehicle
        vehicle = new Vehicle();
        vehicle.setVehicleId("vehicle123");
        vehicle.setUser(user);

        // Initialize Proposal
        proposal = new Proposal();
        proposal.setProposalId("proposal123");
        proposal.setSubmissionDate(LocalDate.now());
        proposal.setStatus("Pending");
        proposal.setUser(user);
        proposal.setOfficer(officer);
        proposal.setVehicle(vehicle);

        new ProposalDTO(proposal);
    }

    @Test
    void testSubmitProposal_Success() {

        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        when(officerRepository.findById(officer.getOfficerId())).thenReturn(Optional.of(officer));
        when(vehicleRepository.save(any(Vehicle.class))).thenReturn(vehicle);
        when(proposalRepository.save(any(Proposal.class))).thenReturn(proposal);

        ProposalDTO submittedProposal = proposalService.submitProposal(proposal);

        assertNotNull(submittedProposal);
        assertEquals(proposal.getProposalId(), submittedProposal.getProposalId());
        verify(proposalRepository, times(1)).save(any(Proposal.class));
    }

    @Test
    void testSubmitProposal_UserNotFound() {

        when(userRepository.findById(user.getUserId())).thenReturn(Optional.empty());

        proposal.setUser(user);
        assertThrows(ResourceNotFoundException.class, () -> proposalService.submitProposal(proposal));
    }

    @Test
    void testSubmitProposal_OfficerNotFound() {

        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
        when(officerRepository.findById(officer.getOfficerId())).thenReturn(Optional.empty());

        proposal.setOfficer(officer);
        assertThrows(ResourceNotFoundException.class, () -> proposalService.submitProposal(proposal));
    }

    @Test
    void testGetProposalById_Success() {

        when(proposalRepository.findById(proposal.getProposalId())).thenReturn(Optional.of(proposal));

        ProposalDTO fetchedProposal = proposalService.getProposalById(proposal.getProposalId());

        assertNotNull(fetchedProposal);
        assertEquals(proposal.getProposalId(), fetchedProposal.getProposalId());
        verify(proposalRepository, times(1)).findById(proposal.getProposalId());
    }

    @Test
    void testGetProposalById_NotFound() {

        when(proposalRepository.findById(proposal.getProposalId())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> proposalService.getProposalById(proposal.getProposalId()));
    }

    @Test
    void testGetAllProposals_Success() {

        List<Proposal> proposals = new ArrayList<>();
        proposals.add(proposal);
        when(proposalRepository.findAll()).thenReturn(proposals);

        List<ProposalDTO> proposalList = proposalService.getAllProposals();

        assertNotNull(proposalList);
        assertEquals(1, proposalList.size());
        verify(proposalRepository, times(1)).findAll();
    }

    @Test
    void testGetAllProposals_Empty() {

        when(proposalRepository.findAll()).thenReturn(new ArrayList<>());

        assertThrows(ResourceNotFoundException.class, () -> proposalService.getAllProposals());
    }

    @Test
    void testApproveProposal_Success() {

        String remarks = "Approved successfully";
        when(proposalRepository.findById(proposal.getProposalId())).thenReturn(Optional.of(proposal));
        when(proposalRepository.save(any(Proposal.class))).thenReturn(proposal);

        ProposalDTO approvedProposal = proposalService.approveProposal(proposal.getProposalId(), remarks);

        assertNotNull(approvedProposal);
        assertEquals("Quote Generated", approvedProposal.getStatus());
        verify(proposalRepository, times(1)).save(any(Proposal.class));
    }

    @Test
    void testRejectProposal_Success() {

        String remarks = "Rejected due to invalid documents";
        when(proposalRepository.findById(proposal.getProposalId())).thenReturn(Optional.of(proposal));
        when(proposalRepository.save(any(Proposal.class))).thenReturn(proposal);

        ProposalDTO rejectedProposal = proposalService.rejectProposal(proposal.getProposalId(), remarks);

        assertNotNull(rejectedProposal);
        assertEquals("Rejected", rejectedProposal.getStatus());
        verify(proposalRepository, times(1)).save(any(Proposal.class));
    }

    @Test
    void testRequestAdditionalDetails_Success() {

        String remarks = "Need additional proof of ownership";
        when(proposalRepository.findById(proposal.getProposalId())).thenReturn(Optional.of(proposal));
        when(proposalRepository.save(any(Proposal.class))).thenReturn(proposal);

        ProposalDTO updatedProposal = proposalService.requestAdditionalDetails(proposal.getProposalId(), remarks);

        assertNotNull(updatedProposal);
        assertEquals("Additional Details Requested", updatedProposal.getStatus());
        verify(proposalRepository, times(1)).save(any(Proposal.class));
    }

    @Test
    void testSendQuote_Success() {

        Policy policy = new Policy();
        policy.setPolicyId("policy123");
        policy.setBasePremium(1000.0);
        policy.setAddOns("Roadside Assistance,Accident Cover");

        proposal.setPolicy(policy);
        proposal.setStatus("Quote Generated");

        when(proposalRepository.findById(proposal.getProposalId())).thenReturn(Optional.of(proposal));
        when(proposalRepository.save(any(Proposal.class))).thenReturn(proposal);

        ProposalDTO quoteProposal = proposalService.sendQuote(proposal.getProposalId());

        assertNotNull(quoteProposal);
        assertTrue(quoteProposal.getRemarks().contains("Total Premium:"));
        verify(proposalRepository, times(1)).save(any(Proposal.class));
    }

    @Test
    void testSendQuote_InvalidStatus() {

        proposal.setStatus("Pending");
        when(proposalRepository.findById(proposal.getProposalId())).thenReturn(Optional.of(proposal));

        assertThrows(IllegalStateException.class, () -> proposalService.sendQuote(proposal.getProposalId()));
    }
}