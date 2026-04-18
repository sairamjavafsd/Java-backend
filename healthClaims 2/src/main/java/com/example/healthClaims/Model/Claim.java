package com.example.healthClaims.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "claims")
public class Claim {

    @Id
    private String id;

    @NotBlank(message = "Patient name cannot be Empty")
    private String patientName;

    @Positive(message = "Claim amount must be greater than zero")
    private double claimAmount;
    private String status;
    private LocalDateTime createdAt;

    public Claim() {
    }

    public Claim(String patientName, double claimAmount,String status){
        this.patientName = patientName;
        this.claimAmount = claimAmount;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
