package com.petopia.petopia.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pet_service_provider_details")
public class PetServiceProviderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "provider_name", nullable = false)
    private String providerName;

    @Column(name = "service_type", nullable = false)
    private String serviceType;

    @Column(name = "license_number")
    private String licenseNumber;

    @Column(name = "experience_years")
    private Integer experienceYears;

    @Column(name = "address_line1", nullable = false)
    private String addressLine1;

    @Column(name = "address_line2")
    private String addressLine2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    private State state;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "website_url")
    private String websiteUrl;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
