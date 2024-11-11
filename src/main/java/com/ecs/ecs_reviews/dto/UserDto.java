package com.ecs.ecs_reviews.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer userId;
    private String username;
    private String password;
    @NotNull
    @NotBlank(message = "Role cannot be blank")
    @NotEmpty(message = "Role cannot be null")
    private String role;
}
