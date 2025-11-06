package com.petopia.petopia.Dto;


import lombok.Data;

@Data
public class UserCacheDTO {

    private Long id;
    private String userName;
    private String email;
    private Long roleId;
}
