package ma.ac.emi.bricool.service;


import ma.ac.emi.bricool.entities.Seller;
import ma.ac.emi.bricool.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    // Create a new seller
    public Seller createSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    // Get all sellers
    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    // Get seller by ID
    public Seller getSellerById(Long id) {
        Optional<Seller> sellerOptional = sellerRepository.findById(id);
        return sellerOptional.orElse(null); // Return null if seller is not found
    }

    // Update seller by ID
    public Seller updateSeller(Long id, Seller updatedSeller) {
        Optional<Seller> existingSellerOptional = sellerRepository.findById(id);
        if (existingSellerOptional.isPresent()) {
            Seller existingSeller = existingSellerOptional.get();
            // Update the fields of the existing seller with the updatedSeller
            // Make sure to handle each field update according to your requirements
//            existingSeller.setCin(updatedSeller.getCin());
//            existingSeller.setBusinessHours(updatedSeller.getBusinessHours());
//            existingSeller.setOperationalRegion(updatedSeller.getOperationalRegion());
//            existingSeller.setCity(updatedSeller.getCity());
//            existingSeller.setSlogan(updatedSeller.getSlogan());
//            existingSeller.setDescription(updatedSeller.getDescription());
//            existingSeller.setRating(updatedSeller.getRating());
//            existingSeller.setCompletedTaskNumber(updatedSeller.getCompletedTaskNumber());
//            existingSeller.setPhotoDeProfil(updatedSeller.getPhotoDeProfil());
//            existingSeller.setYearsOfExperience(updatedSeller.getYearsOfExperience());
//            existingSeller.setOccupations(updatedSeller.getOccupations());
//            existingSeller.setProjects(updatedSeller.getProjects());
//            existingSeller.setRole(updatedSeller.getRole());
            // ... Update other fields similarly

            return sellerRepository.save(updatedSeller);
        } else {
            return null; // Seller not found, return null or throw an exception as needed
        }
    }

    // Delete seller by ID
    public void deleteSeller(Long id) {
        sellerRepository.deleteById(id);
    }


    // save seller
    public void save(Seller seller) {

        sellerRepository.save(seller);
    }


}