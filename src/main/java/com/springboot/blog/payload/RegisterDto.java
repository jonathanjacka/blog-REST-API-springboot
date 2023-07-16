package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//Swagger Docs
@Schema(
        description = "Register Data Transfer Object Model Information - returned on successful registration"
)
public class RegisterDto {
    //Swagger Docs
    @Schema(
            description = "Name of registrant"
    )
    private String name;
    //Swagger Docs
    @Schema(
            description = "Username of registrant - must be unique"
    )
    private String username;
    //Swagger Docs
    @Schema(
            description = "Email of registrant - must be unique"
    )
    private String email;
    //Swagger Docs
    @Schema(
            description = "Password of registrant"
    )
    private String password;
}
