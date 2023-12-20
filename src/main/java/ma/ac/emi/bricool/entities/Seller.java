package ma.ac.emi.bricool.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Seller extends Client{


    //private List<Occupation> occupations = new ArrayList<>();


   // private List <String> regionalOperations;


    //private String photoDeProfil;


    //private String cin;

    //private String businessHours;

    private Ville ville;
    private Date dateOfBirth;
    private String occupation;

//
//    private Gender gender;
//    private String city;
//
//
//    private String slogan;
//
//    private String description;
//
//    private int rating;
//
//    private int completedTAskNumber;
//


   /* @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL, orphanRemoval = true)
    private List <Photo> photos = new ArrayList<>();

    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL, orphanRemoval = true)
   private List <Skill> skills= new ArrayList<>();*/

//    public Seller(String firstName,
//                  String lastName,
//                  String email,
//                  String phone,
//                  String password,
//                  String regionalOperations,
//                  String cin,
//                  String businessHours,
//                  Ville ville,
//                  Gender gender,
//                  String city,
//                  String slogan,
//                  String description,
//                  int rating,
//                  int completedTAskNumber
//    ) {
//
//        super(firstName, lastName, email, phone, password);
//
//       // this.regionalOperations = regionalOperations;
//        this.cin = cin;
//        this.businessHours = businessHours;
//        this.ville = ville;
//        this.gender = gender;
//        this.city = city;
//        this.slogan = slogan;
//        this.description = description;
//        this.rating = rating;
//        this.completedTAskNumber = completedTAskNumber;
//    }


    public Seller(String firstName, String lastName, String email, String gender, String phone, String password, Ville ville, Date dateOfBirth, String occupation) {
        super(firstName, lastName, email, gender, phone, password);
        this.ville = ville;
        this.dateOfBirth = dateOfBirth;
        this.occupation = occupation;
    }


}
