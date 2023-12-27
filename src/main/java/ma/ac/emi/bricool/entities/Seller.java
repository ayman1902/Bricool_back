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
@Builder

public class Seller{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Date yearsOfBirth;
    private String gender;




}
