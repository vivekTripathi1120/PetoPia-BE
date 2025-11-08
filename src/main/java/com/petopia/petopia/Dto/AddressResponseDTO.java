package com.petopia.petopia.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDTO {

    private String line1;
    private String line2;
    private String cityName;
    private String stateName;
    private String stateCode;
    private String countryName;
    private String pincode;
}
