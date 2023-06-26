package com.springboot.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity //needed for roles configuration - allows adding @Preauthorize to controllers
public class SecurityConfig {

    private UserDetailsService userDetailsService;

    //Constructor-based dependency conversion
    public SecurityConfig(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Automatically does auth with userDetailsService and PasswordEncoder, don't need to explicitly specify
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests((auth) ->
                //auth.anyRequest().authenticated() ** Allows access to all roles on any request
                auth.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest()
                        .permitAll()
        ).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    /*

    In-memory objects no longer required
    Imports remain but are unused too

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.builder().username("user").password(passwordEncoder().encode("user")).roles("USER").build();
        UserDetails jonathan = User.builder().username("jonathan").password(passwordEncoder().encode("jonathan")).roles("USER").build();
        UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user, jonathan, admin);
    }
    */
}
