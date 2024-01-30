package ma.ac.emi.bricool.web.public_controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ac.emi.bricool.DTO.incoming_request.RegistrationRequest;
import ma.ac.emi.bricool.DTO.incoming_request.SellerRegistrationRequest;
import ma.ac.emi.bricool.DTO.response.AuthenticationResponse;
import ma.ac.emi.bricool.security.JwtTokenService;
import ma.ac.emi.bricool.security.JwtUserDetailsService;
import ma.ac.emi.bricool.security.RegistrationService;
import ma.ac.emi.bricool.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.security.PrivateKey;


@RestController
@RequiredArgsConstructor
public class RegistrationResource {

    private final RegistrationService registrationService;

   private final JwtTokenService jwtTokenService;

    private final JwtUserDetailsService jwtUserDetailsService;

    private final AppUserService appUserService;



    @PostMapping("/client-register")
    public AuthenticationResponse register(
            @RequestBody @Valid final RegistrationRequest registrationRequest) {
        if (registrationService.emailExists(registrationRequest)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "registration.register.taken");
        }
        registrationService.registerClient(registrationRequest);
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(registrationRequest.getEmail());
        final AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setAccessToken(jwtTokenService.generateToken(userDetails));
        authenticationResponse.setUser(
                appUserService.getByEmail(registrationRequest.getEmail()));

        return authenticationResponse;

        //faire la meme chose que le seller
    }

    @PostMapping("/seller-register")
    public AuthenticationResponse registerSeller(
            @RequestBody @Valid final SellerRegistrationRequest sellerRegistrationRequest) {
        if (registrationService.emailExists(sellerRegistrationRequest)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "registration.register.taken");
        }
        registrationService.registerSeller(sellerRegistrationRequest);

        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(sellerRegistrationRequest.getEmail());
        final AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setAccessToken(jwtTokenService.generateToken(userDetails));
        authenticationResponse.setUser(
                appUserService.getByEmail(sellerRegistrationRequest.getEmail()));

        return authenticationResponse;
    }

}
