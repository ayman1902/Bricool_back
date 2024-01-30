package ma.ac.emi.bricool.entities;



import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@ToString
@DiscriminatorValue("admin")
public class Admin extends AppUser {

   private String photoDeProfile;

    private String role= UserRole.ADMIN;
    @Override
    public String getRole() {
        return UserRole.ADMIN;
    }

   public Admin(String firstName, String lastName, String email, String phoneNumber, String password) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
        this.setPassword(password);
    }


}
