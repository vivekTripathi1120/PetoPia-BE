package com.petopia.petopia.controller;

import com.petopia.petopia.Dto.UserRequestDTO;
import com.petopia.petopia.Dto.UserResponseDTO;
import com.petopia.petopia.service.ClientService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@PreAuthorize("hasRole('USER')")
@RestController
@RequestMapping("/user")
public class ClientController {

    Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    ClientService clientService;

    @PostMapping("/unsecure/registration")
    private ResponseEntity<UserResponseDTO> clientRegistration(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(clientService.clientRegistration(userRequestDTO));
    }


    @GetMapping("/getUser")
    public ResponseEntity<UserResponseDTO> getUser(@RequestParam Long userId, HttpServletRequest httpRequest){
        logger.info("header..{}", Arrays.toString(httpRequest.getHeaders("Authorization").toString().split("Bearer ")));

        return ResponseEntity.ok(clientService.getUser(userId));
    }
}
