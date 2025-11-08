package com.petopia.petopia.controller;

import com.petopia.petopia.Dto.UserAddressRequestDTO;
import com.petopia.petopia.Dto.UserRequestDTO;
import com.petopia.petopia.Dto.UserResponseDTO;
import com.petopia.petopia.cache.UserCacheDTO;
import com.petopia.petopia.service.ClientService;
import com.petopia.petopia.utils.AuthUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class ClientController {

    Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    ClientService clientService;

    @Autowired
    AuthUtils authUtils;

    @PostMapping("/unsecure/registration")
    public ResponseEntity<UserResponseDTO> clientRegistration(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(clientService.clientRegistration(userRequestDTO));
    }


    @GetMapping("/getUser")
    public ResponseEntity<UserResponseDTO> getUser(@RequestParam Long userId, HttpServletRequest httpRequest){

        return ResponseEntity.ok(clientService.getUser(userId));
    }

    @PostMapping("/updateUser")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO userRequestDTO, HttpServletRequest httpRequest){

        UserCacheDTO cacheDTO = authUtils.extractUserDetails(httpRequest);
        return ResponseEntity.ok(clientService.updateUserDetails(userRequestDTO,cacheDTO));
    }

    @PostMapping("/updateUserAddress")
    public ResponseEntity<UserResponseDTO> updateUserAddress(@RequestBody UserAddressRequestDTO userRequestDTO,
                                                             HttpServletRequest httpRequest){

        UserCacheDTO cacheDTO = authUtils.extractUserDetails(httpRequest);
        return ResponseEntity.ok(clientService.updateUserAddress(userRequestDTO,cacheDTO));
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<UserResponseDTO> deleteUser(@RequestParam Long userId, HttpServletRequest httpServletRequest){
        UserCacheDTO cacheDTO = authUtils.extractUserDetails(httpServletRequest);

        return ResponseEntity.ok(clientService.deleteUserDetails(userId,cacheDTO));
    }

}
