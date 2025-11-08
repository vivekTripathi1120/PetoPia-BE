package com.petopia.petopia.utils;

import com.mysql.cj.util.StringUtils;
import com.petopia.petopia.cache.UserCacheDTO;
import com.petopia.petopia.entity.User;
import com.petopia.petopia.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthUtils authUtils;

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        logger.info("request incoming...{}",request.getRequestURI());

        String requestToken = request.getHeader("Authorization");

        if(StringUtils.isNullOrEmpty(requestToken) || !requestToken.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }

        String userName;
        UserCacheDTO userDetails = authUtils.extractUserDetails(request);
        if(!StringUtils.isNullOrEmpty(userDetails.getEmail())){
         userName = userDetails.getEmail();
        }else {
            userName = userDetails.getUserName();
        }

        if(null != userName && null ==  SecurityContextHolder.getContext().getAuthentication()){
            User user = userRepository.findByUsername(userName);
            if(null != user && user.getEmail().equals(userName)){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName,
                        null,user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request,response);
        }


    }
}
