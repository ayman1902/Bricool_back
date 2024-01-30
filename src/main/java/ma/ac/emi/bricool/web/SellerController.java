package ma.ac.emi.bricool.web;

import ma.ac.emi.bricool.entities.Seller;
import ma.ac.emi.bricool.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/sellers")
//@CrossOrigin(origins = "*")
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

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getSynthImage(@PathVariable("id") long id) {
        try {
            Seller seller = sellerService.getSellerById(id);
            Path publicDirectory = Paths.get(".", "public").toAbsolutePath();
            Path filepath = publicDirectory.resolve(seller.getPhotoDeProfil());

            if (!Files.exists(filepath)) {
                throw new FileNotFoundException("Image file not found");
            }

            byte[] imageData = Files.readAllBytes(filepath);
            String contentType = "image/jpeg"; // Adjust this based on the actual image type

            return ResponseEntity
                    .ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(imageData);

        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Log exception details here
            return ResponseEntity.internalServerError().build();
        }
    }



    @PostMapping("/{id}/image")
    public void createSynthImage(@PathVariable("id") long id, @RequestParam("file") MultipartFile image) throws Exception {
        Seller seller = sellerService.getSellerById(id);
        // The "public" directory is automatically used by Spring to serve static assets
        Path publicDirectory = Paths.get(".", "public").toAbsolutePath();

        if (!Files.exists(publicDirectory)) {
            Files.createDirectories(publicDirectory);
        }

        byte[] imageContent = image.getBytes();
        Path filepath = Paths.get(publicDirectory.toString(), image.getOriginalFilename());
        try (OutputStream os = Files.newOutputStream(filepath)) {
            os.write(imageContent);
        }
        seller.setPhotoDeProfil(image.getOriginalFilename());
        sellerService.save(seller);
    }

}