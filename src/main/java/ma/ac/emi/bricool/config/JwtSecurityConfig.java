package ma.ac.emi.bricool.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import ma.ac.emi.bricool.DTO.response.ErrorResponse;

import jakarta.servlet.http.HttpServletResponse;
import ma.ac.emi.bricool.config.JwtRequestFilter;
import ma.ac.emi.bricool.entities.UserRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class JwtSecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;
    private final ObjectMapper objectMapper;

    public JwtSecurityConfig(final JwtRequestFilter jwtRequestFilter,
            final ObjectMapper objectMapper) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.objectMapper = objectMapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            final AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Utilisez directement les méthodes pour configurer CORS et CSRF si elles sont disponibles.
                .cors(withDefaults())
                .csrf(csrf-> csrf.disable())
                .authorizeRequests(authz -> authz
                        .requestMatchers("/api/**").hasAnyAuthority(UserRole.ADMIN, UserRole.SELLER, UserRole.CLIENT)
                        .requestMatchers("/clients/**").hasAnyAuthority(UserRole.CLIENT, UserRole.ADMIN)
                        .requestMatchers("/sellers/**").hasAnyAuthority(UserRole.SELLER, UserRole.ADMIN)
                        .anyRequest().permitAll()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint((request, response, ex) -> {
                            ErrorResponse errorResponse = new ErrorResponse();
                            errorResponse.setException("Unauthorized");
                            errorResponse.setHttpStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.getOutputStream().println(objectMapper.writeValueAsString(errorResponse));
                        })
                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
