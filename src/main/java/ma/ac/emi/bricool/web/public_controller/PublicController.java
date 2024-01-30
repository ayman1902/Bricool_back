package ma.ac.emi.bricool.web.public_controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import ma.ac.emi.bricool.service.SellerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api-public/", produces = MediaType.APPLICATION_JSON_VALUE)
@SecurityRequirement(name = "bearer-jwt")
@RequiredArgsConstructor
public class PublicController {

    private final SellerService sellerService;



  //add getMapping for getting sellers cards withe order by rating


}
