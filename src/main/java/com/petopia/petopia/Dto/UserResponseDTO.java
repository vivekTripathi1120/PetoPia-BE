package com.petopia.petopia.Dto;


import lombok.Data;

@Data
public class UserResponseDTO {


    private String userName;
    private String password;
    private String email;
    private String message;
    private Boolean status;
}
