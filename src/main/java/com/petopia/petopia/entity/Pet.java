package com.petopia.petopia.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to user_details table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserDetails user;

    // Link to breed table
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "breed_id", nullable = false)
    private Breed breed;

    @Column(name = "pet_name", nullable = false)
    private String petName;

    @Column(name = "pet_age")
    private Integer petAge;

    @Column(name = "pet_gender")
    private String petGender;

    @Column(name = "pet_weight", precision = 10, scale = 2)
    private BigDecimal petWeight;

    @Column(name = "color")
    private String color;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "created_by")
    private String createdBy = "USER";

    @Column(name = "updated_by")
    private String updatedBy = "USER";

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
