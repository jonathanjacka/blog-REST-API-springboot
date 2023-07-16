package com.springboot.blog.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "Login Data Transfer Object Model Information - sent as payload on login request"
)
public class LoginDto {
    @Schema(
            description = "Username or email of user"
    )
    private String usernameOrEmail;
    @Schema(
            description = "Password of user"
    )
    private String password;

}
