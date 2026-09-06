package uy.com.emptyloop.pilatesaas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/api/tenants").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/tenants").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/tenants/{}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/tenants").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/tenants").permitAll()
                .anyRequest().authenticated()
            );

        return http.build();
    }
}