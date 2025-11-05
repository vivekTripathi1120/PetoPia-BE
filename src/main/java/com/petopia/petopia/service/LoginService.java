package com.petopia.petopia.service;


import com.petopia.petopia.Dto.LoginRequestDTO;
import com.petopia.petopia.Dto.LoginResponseDTO;

public interface LoginService {

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO);
}
