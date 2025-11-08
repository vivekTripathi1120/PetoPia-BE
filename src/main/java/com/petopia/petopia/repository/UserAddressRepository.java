package com.petopia.petopia.repository;

import com.petopia.petopia.Dto.AddressResponseDTO;
import com.petopia.petopia.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

  @Query("""
          SELECT new com.petopia.petopia.Dto.AddressResponseDTO(
              ua.line1,
              ua.line2,
              c.cityName,
              s.stateName,
              s.stateCode,
              cntry.countryName,
              ua.pincode
          )
          FROM 
          UserAddress ua 
          LEFT JOIN City c on ua.cityId = c.id
          LEFT JOIN State s on c.state.id = s.id
          LEFT JOIN Country cntry on s.country.id = cntry.id
          where userDetails.id = :userId 
          and ua.isDeleted = false and ua.isActive = true
          and c.isDeleted = false and c.isActive = true
          and s.isDeleted = false and s.isActive = true
          and cntry.isDeleted = false and cntry.isActive = true
          """)
  AddressResponseDTO findByUserIdAndStatus(Long userId);

  @Query(" Select u FROM UserAddress u where u.userDetails.id = :userId and isDeleted = false and isActive = true ")
  UserAddress findByUserIdAndIsDeletedFalse(Long userId);
}