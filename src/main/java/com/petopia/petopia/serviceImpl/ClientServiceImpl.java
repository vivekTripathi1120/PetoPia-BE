package com.petopia.petopia.serviceImpl;

import com.petopia.petopia.Dto.UserRequestDTO;
import com.petopia.petopia.Dto.UserResponseDTO;
import com.petopia.petopia.entity.User;
import com.petopia.petopia.entity.UserDetails;
import com.petopia.petopia.repository.UserDetailsRepository;
import com.petopia.petopia.repository.UserRepository;
import com.petopia.petopia.service.ClientService;
import com.petopia.petopia.utils.PetoPiaConstants;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Transactional
    public UserResponseDTO clientRegistration(UserRequestDTO userRequestDTO) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        UserDetails existingUser = userDetailsRepository.findByUserEmail(userRequestDTO.getEmail());

        if(null != existingUser){
            responseDTO.setMessage(PetoPiaConstants.USER_ALREADY_EXIST);
            responseDTO.setStatus(false);
            return responseDTO;
        }
        LocalDateTime now = LocalDateTime.now();
        UserDetails userDetails = getUserDetails(userRequestDTO, now);
        userDetailsRepository.save(userDetails);
        saveUserDetailsInUser(userRequestDTO,userDetails.getId(),now);
        responseDTO.setUserName(userDetails.getUserName());
        responseDTO.setMessage(PetoPiaConstants.REGISTRATION_SUCCESS_MSG);
        responseDTO.setStatus(true);
        return responseDTO;
    }

    private void saveUserDetailsInUser(UserRequestDTO userDetails, Long id, LocalDateTime now) {

        User user = new User();
        user.setEmail(userDetails.getEmail());
        user.setUsername(userDetails.getUserName());
        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        user.setUserId(id);
        user.setRoleId(PetoPiaConstants.USER_ROLE);
        user.setIsDeleted(false);
        user.setCreatedAt(now);
        userRepository.save(user);
    }

    private static UserDetails getUserDetails(UserRequestDTO userRequestDTO, LocalDateTime now) {
        UserDetails userDetails = new UserDetails();
        userDetails.setUserName(userRequestDTO.getUserName());
        userDetails.setEmail(userRequestDTO.getEmail());
        userDetails.setPhoneNumber(userDetails.getPhoneNumber());
        userDetails.setIsActive(true);
        userDetails.setIsDeleted(false);
        userDetails.setCreatedAt(now);
        userDetails.setUpdatedAt(now);
        userDetails.setPhoneNumber(userRequestDTO.getPhoneNumber());

        return userDetails;
    }
}
