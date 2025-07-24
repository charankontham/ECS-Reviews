package com.ecs.ecs_reviews.service;

import com.ecs.ecs_reviews.dto.AdminDto;
import com.ecs.ecs_reviews.dto.CustomerDto;
import com.ecs.ecs_reviews.dto.UserPrincipal;
import com.ecs.ecs_reviews.exception.ResourceNotFoundException;
import com.ecs.ecs_reviews.feign.AdminService;
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

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, ResourceNotFoundException {
        try {
            ResponseEntity<CustomerDto> customerResponse = customerService.getCustomerByEmail(username);
            if (Objects.nonNull(customerResponse.getBody()) || customerResponse.getStatusCode() == HttpStatus.OK) {
                return new UserPrincipal(customerResponse.getBody());
            }
        }catch(Exception e) {
            System.out.println(e);
            ResponseEntity<AdminDto> adminResponse = adminService.getByUsername(username);
            if(Objects.nonNull(adminResponse.getBody()) || adminResponse.getStatusCode() == HttpStatus.OK){
                return new UserPrincipal(adminResponse.getBody());
            }
        }
        throw new ResourceNotFoundException("User not found");
    }
}
