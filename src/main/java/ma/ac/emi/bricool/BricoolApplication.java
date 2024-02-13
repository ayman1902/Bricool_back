package ma.ac.emi.bricool;

import ma.ac.emi.bricool.entities.AppUser;
import ma.ac.emi.bricool.entities.Client;
import ma.ac.emi.bricool.entities.Project;
import ma.ac.emi.bricool.entities.Seller;
import ma.ac.emi.bricool.repositories.AppUserRepository;
import ma.ac.emi.bricool.repositories.ClientRepository;
import ma.ac.emi.bricool.repositories.ProjectRepository;
import ma.ac.emi.bricool.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BricoolApplication implements CommandLineRunner{




    public static void main(String[] args) {
        SpringApplication.run(BricoolApplication.class, args);
    }

/*    @Override
    public void run(String... args) throws Exception {

        clientRepository.save(new Client("ahmed",
                "echarfaouy", "mohamed@gmail.com",
                "98364962936",
                "mohamed"));

    }*/
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    @Override
    public void run(String... args) {
        // Create and save 6 projects
        createAndSaveProjects();
    }

    private void createAndSaveProjects() {
        // Create and save 6 projects
        Project project3 = Project.builder()
                .projectName("Project 3")
                .description("Description 3")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusMonths(3))
                .budget(10000.0)
                .status("Active")
                .serviceType("Service Type 2")
                .location("Location 2")
                .bookingAvailability("Available")
                .photos(Arrays.asList("photo1.jpg", "photo2.jpg"))
                .build();

        // Repeat this for the other projects...

        // Save projects to the database
    }
}
