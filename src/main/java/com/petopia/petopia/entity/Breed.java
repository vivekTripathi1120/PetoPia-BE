package com.petopia.petopia.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "breeds")
public class Breed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "breed_name", nullable = false)
    private String breedName;

    @Column(name = "animal_type", nullable = false)
    private String animalType;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "created_by")
    private String createdBy = "ADMIN";

    @Column(name = "updated_by")
    private String updatedBy = "ADMIN";

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
}
