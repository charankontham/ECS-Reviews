package com.ecs.ecs_reviews.service;

import com.ecs.ecs_reviews.dto.CustomerDto;
import com.ecs.ecs_reviews.dto.UserPrincipal;
import com.ecs.ecs_reviews.exception.ResourceNotFoundException;
import com.ecs.ecs_reviews.feign.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserAuthenticationDetails implements UserDetailsService {
    @Autowired
    private CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ResponseEntity<CustomerDto> response = customerService.getCustomerByEmail(username);
        if (Objects.isNull(response.getBody()) || response.getStatusCode() != HttpStatus.OK) {
            throw new ResourceNotFoundException("Customer not found!");
        } else {
            return new UserPrincipal(response.getBody());
        }
    }
}
