package ma.ac.emi.bricool.DTO.user_management;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class SellerDTO {

    private Long Id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Date yearsOfBirth;
    private String gender;

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

    private String role;
}
