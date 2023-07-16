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
        description = "Auth Data Transfer Object Model Information - returned on successful login"
)
public class JwtAuthResponse {

    @Schema(
            description = "JWT Access Token"
    )
    private String accessToken;
    @Schema(
            description = "Indicates token type required for subsequent requests"
    )
    private String tokenType = "Bearer";
}
