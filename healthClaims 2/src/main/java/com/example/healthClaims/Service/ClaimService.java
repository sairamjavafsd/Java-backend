package com.example.healthClaims.Service;

import com.example.healthClaims.Model.Claim;
import com.example.healthClaims.Model.ClaimResponse;
import com.example.healthClaims.Repository.ClaimRepository;
import com.example.healthClaims.exception.ClaimNotFoundException;
import com.example.healthClaims.exception.InvalidClaimStateException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;


@Service
public class ClaimService {

    @Autowired
    private ClaimRepository repository;

    private static final String NEW = "NEW";
    private static final String APPROVED = "APPROVED";
    private static final String REJECTED = "REJECTED";


    public ClaimResponse getClaimById(String id){
       log.info("Fetching claim with id: {}", id);
        Claim claim = repository.findById(id).orElseThrow(() -> new ClaimNotFoundException("Claim Not Found with ID " + id));

        return mapToResponse(claim);
    }

    public List<ClaimResponse> getAllClaims(int page, int size){

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        Page<Claim> claimPage = repository.findAll(pageable);

        return claimPage.getContent().stream().map(this::mapToResponse).toList();
    }

    public ClaimResponse updateClaim(String id, Claim updatedClaim){
        log.info("Updating claim with id: {}", id);
        Claim existing = getClaimEntityById(id);
        existing.setPatientName(updatedClaim.getPatientName());
        existing.setClaimAmount((updatedClaim.getClaimAmount()));
        Claim saved = repository.save(existing);
        return mapToResponse(saved);
    }

    private Claim getClaimEntityById(String id) {
        return repository.findById(id).orElseThrow(() -> new ClaimNotFoundException("Claim not found with ID"+ id));
    }

    public void deleteClaim(String id){
        repository.deleteById(id);
    }

    public ClaimResponse approveClaim(String id){
        log.info("Approving claim with id: {}", id);
        Claim claim = getClaimEntityById(id);
        log.info("Current status before action; {}", claim.getStatus());
        if (!NEW.equals(claim.getStatus())){
            throw new InvalidClaimStateException("Only new claims can be approved");
        }
        claim.setStatus(APPROVED);
        Claim saved  = repository.save(claim);
        return mapToResponse(saved);
    }

    public ClaimResponse rejectClaim(String id){
        log.info("Rejecting claim with id: {}", id);
        Claim claim = getClaimEntityById(id);
        log.info("Current status before action; {}", claim.getStatus());
        if (!NEW.equals(claim.getStatus())){
            throw new InvalidClaimStateException("Only New claims can be rejected");
        }
        claim.setStatus(REJECTED);
        Claim saved = repository.save(claim);
        return mapToResponse(saved);
    }

    public List<Claim> getByStatus(String status){
        return repository.findByStatus(status);
    }

    private static final Logger log = LoggerFactory.getLogger(ClaimService.class);

    public ClaimResponse createClaim(Claim claim){
        log.info("Creating claim for patient: {}", claim.getPatientName());
        if(claim.getClaimAmount() < 5000){
            claim.setStatus(APPROVED);
            log.info("Claim Auto Approved (amount < 5000)");
        }
        else {
            claim.setStatus(NEW);
        }

        claim.setCreatedAt(LocalDateTime.now());
        Claim saved = repository.save(claim);

        log.info("Claim saved with id: {}", saved.getId());

        return mapToResponse(saved);
    }

    private ClaimResponse mapToResponse(Claim claim){
        ClaimResponse response = new ClaimResponse();
        response.setId(claim.getId());
        response.setPatientName(claim.getPatientName());
        response.setClaimAmount(claim.getClaimAmount());
        response.setStatus(claim.getStatus());
        response.setCreatedAt(claim.getCreatedAt());
        return response;
    }
}

