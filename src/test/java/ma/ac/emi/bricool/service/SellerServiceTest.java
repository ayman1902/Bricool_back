package ma.ac.emi.bricool.service;

import ma.ac.emi.bricool.entities.Seller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SellerServiceTest {
    @Autowired
    private SellerService sellerService;

    @Test
    private void addSeller(){
        Seller sellerToAdd = Seller.builder()
                .cin("ABC123")
                .businessHours("9 AM - 6 PM")
                .operationalRegion("Region Name")
                .city("City Name")
                .slogan("Slogan")
                .description("Description")
                .rating(4.5)
                .completedTaskNumber(20)
                .photoDeProfil("profile.jpg")
                .yearsOfExperience(5)
                .occupations(List.of("Occupation 1", "Occupation 2"))
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .password("password123")
                .phoneNumber("1234567890")
                .gender("Male")
                .build();

        Seller savedSeller = sellerService.createSeller(sellerToAdd);

    }

}