package ma.ac.emi.bricool.security;


import lombok.extern.slf4j.Slf4j;
import ma.ac.emi.bricool.DTO.incoming_request.SellerRegistrationRequest;
import ma.ac.emi.bricool.entities.Client;
import ma.ac.emi.bricool.entities.Seller;
import ma.ac.emi.bricool.repositories.AdminRepository;
import ma.ac.emi.bricool.DTO.incoming_request.RegistrationRequest;
import ma.ac.emi.bricool.repositories.ClientRepository;
import ma.ac.emi.bricool.repositories.SellerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class RegistrationService {
   // private final AdminRepository adminRepository;

    private final ClientRepository clientRepository;

    private final SellerRepository sellerRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(final ClientRepository clientRepository,
                               final PasswordEncoder passwordEncoder,
                             //  final AdminRepository adminRepository,
                               final SellerRepository sellerRepository) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
       // this.adminRepository = adminRepository;
        this.sellerRepository = sellerRepository;
    }

    public boolean emailExists(final RegistrationRequest registrationRequest) {
        return clientRepository.existsByEmailIgnoreCase(registrationRequest.getEmail());
    }

    public boolean emailExists(final SellerRegistrationRequest registrationRequest) {
        return sellerRepository.existsByEmailIgnoreCase(registrationRequest.getEmail());
    }

    public void registerClient(final RegistrationRequest registrationRequest) {
        log.info("registering new client: {}", registrationRequest.getEmail());

        Client appUser = new Client();
        appUser.setFirstName(registrationRequest.getFirstName());
        appUser.setLastName(registrationRequest.getLastName());
        appUser.setEmail(registrationRequest.getEmail());
        appUser.setPhoneNumber(registrationRequest.getPhoneNumber());
        appUser.setYearsOfBirth(registrationRequest.getYearsOfBirth());
        appUser.setGender(registrationRequest.getGender());
        appUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        clientRepository.save(appUser);
    }

    public void registerSeller(final SellerRegistrationRequest registrationRequest) {
        log.info("registering new seller: {}", registrationRequest.getEmail());

        Seller appUser = new Seller();
        appUser.setFirstName(registrationRequest.getFirstName());
        appUser.setLastName(registrationRequest.getLastName());
        //appUser.setCin(registrationRequest.getCin());
        appUser.setEmail(registrationRequest.getEmail());
        appUser.setPhoneNumber(registrationRequest.getPhoneNumber());
        appUser.setYearsOfBirth(registrationRequest.getYearsOfBirth());
        appUser.setGender(registrationRequest.getGender());
        appUser.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        appUser.setCity(registrationRequest.getCity());
        appUser.addOccupation(registrationRequest.getOccupation());
        sellerRepository.save(appUser);
    }


}
