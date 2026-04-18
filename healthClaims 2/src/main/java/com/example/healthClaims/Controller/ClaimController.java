package com.example.healthClaims.Controller;

import com.example.healthClaims.Model.Claim;
import com.example.healthClaims.Model.ClaimRequest;
import com.example.healthClaims.Model.ClaimResponse;
import com.example.healthClaims.Service.ClaimService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/claims")
@CrossOrigin(origins = "http://localhost:3000")
public class ClaimController {

    @Autowired
    private ClaimService service;

    @PostMapping
    public ClaimResponse createClaim(@Valid @RequestBody ClaimRequest request){
        Claim claim1 = new Claim();
        claim1.setPatientName(request.getPatientName());
        claim1.setClaimAmount(request.getClaimAmount());
        return service.createClaim(claim1);
    }



    @GetMapping
    public List<ClaimResponse> getAllClaims(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        return service.getAllClaims(page, size);
    }

    @GetMapping("/{id}")
    public ClaimResponse getClaim(@PathVariable String id){
        return service.getClaimById(id);
    }

    @PutMapping("/{id}")
    public ClaimResponse updateClaim(@PathVariable String id, @RequestBody Claim claim){
        return service.updateClaim(id, claim);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClaim(@PathVariable String id){
        service.deleteClaim(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/approve")
    public ClaimResponse approveClaim(@PathVariable String id){
        return service.approveClaim(id);
    }

    @PutMapping("/{id}/reject")
    public ClaimResponse rejectClaim(@PathVariable String id){
        return service.rejectClaim(id);
    }

    @PutMapping("/status/{status}")
    public List<Claim> getByStatus(@PathVariable String status){
        return service.getByStatus(status);
    }
}
