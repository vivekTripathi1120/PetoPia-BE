package com.petopia.petopia.Dto;


import lombok.Data;

@Data
public class UserResponseDTO {


    private Long userId;
    private String userName;
    private String email;
    private AddressResponseDTO address;
    private String message;
    private Boolean status;
}
