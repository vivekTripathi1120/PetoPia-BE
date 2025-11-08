package com.petopia.petopia.Dto;


import lombok.Data;

@Data
public class UserRequestDTO {

    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    private String name;
    private Long roleId;

    private UserAddressRequestDTO address;
}
