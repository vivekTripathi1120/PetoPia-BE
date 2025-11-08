package com.petopia.petopia.service;

import com.petopia.petopia.Dto.UserAddressRequestDTO;
import com.petopia.petopia.Dto.UserRequestDTO;
import com.petopia.petopia.Dto.UserResponseDTO;
import com.petopia.petopia.cache.UserCacheDTO;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {

    UserResponseDTO clientRegistration(UserRequestDTO userRequestDTO);

    UserResponseDTO getUser(Long userId);

    UserResponseDTO updateUserDetails(UserRequestDTO userRequestDTO,UserCacheDTO cacheDTO);

    UserResponseDTO updateUserAddress(UserAddressRequestDTO userRequestDTO, UserCacheDTO cacheDTO);

    UserResponseDTO deleteUserDetails(Long userId, UserCacheDTO cacheDTO);
}
