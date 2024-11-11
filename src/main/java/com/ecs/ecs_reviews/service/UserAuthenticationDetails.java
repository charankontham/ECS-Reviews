package com.ecs.ecs_reviews.service;

import com.ecs.ecs_reviews.dto.UserDto;
import com.ecs.ecs_reviews.dto.UserPrincipal;
import com.ecs.ecs_reviews.exception.ResourceNotFoundException;
import com.ecs.ecs_reviews.feign.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationDetails implements UserDetailsService {
    @Autowired
    private CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (customerService.getUserByUsername(username).getStatusCode() != HttpStatus.OK) {
            throw new ResourceNotFoundException("User not found!");
        } else {
            UserDto userDto = customerService.getUserByUsername(username).getBody();
            return new UserPrincipal(userDto);
        }
    }
}
