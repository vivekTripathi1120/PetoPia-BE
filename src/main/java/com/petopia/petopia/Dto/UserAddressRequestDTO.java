package com.petopia.petopia.Dto;

import lombok.Data;

@Data
public class UserAddressRequestDTO {

    private String line1;
    private String line2;
    private Long cityId;
    private Long stateId;
    private String stateCode;
    private String pincode;
    private Long countryId;
}
