package com.petopia.petopia.controller;

import com.petopia.petopia.Dto.UserRequestDTO;
import com.petopia.petopia.Dto.UserResponseDTO;
import com.petopia.petopia.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping("/unsecure/registration")
    private ResponseEntity<UserResponseDTO> clientRegistration(@RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(clientService.clientRegistration(userRequestDTO));
    }
}
