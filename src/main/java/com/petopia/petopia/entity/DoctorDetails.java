package com.petopia.petopia.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "doctor_details")
public class DoctorDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "consultation_fee", precision = 10, scale = 2)
    private BigDecimal consultationFee;

    @Column(name = "clinic_name")
    private String clinicName;

    @Column(name = "clinic_address")
    private String clinicAddress;

    @Column(name = "is_verified")
    private Boolean isVerified = false;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
