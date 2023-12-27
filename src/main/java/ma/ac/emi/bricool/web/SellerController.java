package ma.ac.emi.bricool.web;

import ma.ac.emi.bricool.entities.Seller;
import ma.ac.emi.bricool.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    // Create a new seller
    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) {
        Seller createdSeller = sellerService.createSeller(seller);
        return new ResponseEntity<>(createdSeller, HttpStatus.CREATED);
    }

    // Get all sellers
    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers() {
        List<Seller> sellers = sellerService.getAllSellers();
        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }

    // Get seller by ID
    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable("id") Long id) {
        Seller seller = sellerService.getSellerById(id);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    // Update seller by ID
    @PutMapping("/{id}")
    public ResponseEntity<Seller> updateSeller(@PathVariable("id") Long id, @RequestBody Seller seller) {
        Seller updatedSeller = sellerService.updateSeller(id, seller);
        return new ResponseEntity<>(updatedSeller, HttpStatus.OK);
    }

    // Delete seller by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable("id") Long id) {
        sellerService.deleteSeller(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}