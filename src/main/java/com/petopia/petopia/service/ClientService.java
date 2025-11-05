package com.petopia.petopia.service;

import com.petopia.petopia.Dto.UserRequestDTO;
import com.petopia.petopia.Dto.UserResponseDTO;

public interface ClientService {

    UserResponseDTO clientRegistration(UserRequestDTO userRequestDTO);
}
