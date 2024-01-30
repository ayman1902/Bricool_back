package ma.ac.emi.bricool.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("client")
public class Client extends AppUser {



    private String photoDeProfile;

    private String role= UserRole.CLIENT;


    @Override
    public String getRole() {
        return UserRole.CLIENT;
    }
}
