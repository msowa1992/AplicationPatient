package com.example.demo.config;

import java.util.Objects;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true,jsr250Enabled = true)
public class SecurityConfig {
    final UserService userService;
    final AuthPasswordConfig authPasswordConfig;

    private static final String[] PUBLIC_URI={"/",
    "/contact","/user/add","/user/save",

};



    @Bean 
    DaoAuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(
            email -> userService.findUserByEmail(email)
            .map(user -> User.withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build()
            ).orElseThrow(() ->  new UsernameNotFoundException(""))
        );
        authenticationProvider.setPasswordEncoder(authPasswordConfig.passwordEncoder());
        return authenticationProvider;

    }



    @Transactional
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
        .csrf(httpSecurityCsrfConfigurer->httpSecurityCsrfConfigurer.ignoringRequestMatchers("/api/**","/login") )
        .formLogin(login-> login.loginPage("/login").defaultSuccessUrl("/").permitAll()
        .successHandler((request, respone, authentication)->{
            respone.setStatus(HttpStatus.OK.value());
            var client = request.getParameter("client");

            if( Objects.nonNull(client)){
                var email = request.getParameter("username");
                var optionalUser= userService.findUserByEmail(email);
                if(optionalUser.isPresent()){
                    var objectMapper= new ObjectMapper();
                    respone.setCharacterEncoding("UTF8");
                    respone.setContentType("json");

                    respone.getWriter().write(objectMapper.writeValueAsString(optionalUser.get()));
                }else{
                    respone.setStatus(HttpStatus.NOT_FOUND.value());
                }

            }else{
                respone.sendRedirect("/");
            }
        }))
        .logout(logout-> logout.logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .permitAll()
        )   
        .authorizeHttpRequests((auth)-> auth.requestMatchers(PUBLIC_URI).permitAll()
            .anyRequest().authenticated())
        .build();


    }

    

}
