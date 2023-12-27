package ma.ac.emi.bricool.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Seller extends Client{

    @Id
    @GeneratedValue
    private Long sellerId;
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
    private List<String> occupations;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="seller_id", referencedColumnName = "sellerId")
    private List<Project> projects;




}
