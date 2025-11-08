package com.petopia.petopia.cache;


import lombok.Data;

@Data
public class UserCacheDTO {

    private Long userId;
    private String userName;
    private String email;
    private Long roleId;
}
