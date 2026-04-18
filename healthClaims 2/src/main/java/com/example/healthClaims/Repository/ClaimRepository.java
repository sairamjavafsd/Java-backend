package com.example.healthClaims.Repository;

import com.example.healthClaims.Model.Claim;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClaimRepository extends MongoRepository<Claim, String> {
    List<Claim> findByStatus(String status);
}
