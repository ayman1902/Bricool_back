package ma.ac.emi.bricool.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DiscriminatorValue("seller")

public class Seller extends AppUser{


    private String cin;
    private String businessHours;
    private String operationalRegion;
    private String city;
    private String slogan;
    private String description;
    private Double rating;
    private Integer completedTaskNumber;
    private String photoDeProfil;
    private Integer yearsOfExperience;

    @ElementCollection
    private List<String> occupations= new ArrayList<>();

    @OneToMany(mappedBy = "seller")
    @JsonIgnore
    private List<Project> projects;

    private String role= UserRole.SELLER;


    @Override
    public String getRole() {
        return UserRole.SELLER;
    }


    public void addOccupation(String occupation){
        this.occupations.add(occupation);
    }
}
