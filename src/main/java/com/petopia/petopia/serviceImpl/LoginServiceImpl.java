package com.petopia.petopia.serviceImpl;

import com.petopia.petopia.Dto.LoginRequestDTO;
import com.petopia.petopia.Dto.LoginResponseDTO;
import com.petopia.petopia.entity.User;
import com.petopia.petopia.service.LoginService;
import com.petopia.petopia.utils.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    AuthUtils authUtils;

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUserName()
                        ,loginRequestDTO.getPassword()));

        if(null == authenticate){
            throw new UsernameNotFoundException("User Not Found with id..." + loginRequestDTO.getUserName());
        }

        User user = (User) authenticate.getPrincipal();
        String token = authUtils.generateJwtToken(user);

        return LoginResponseDTO
                .builder()
                .jwtToken(token)
                .build();
    }
}
