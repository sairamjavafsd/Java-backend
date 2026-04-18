package com.example.healthClaims.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ClaimRequest {

    @NotBlank(message = "PatientName cannot be blank")
    private String patientName;

    @Positive(message = "Amount should be greater then zero")
    private double claimAmount;

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
}
