package ma.ac.emi.bricool.DTO.user_management;


import lombok.*;

import java.sql.Date;

@Getter
@Setter
public class AdminDTO {
    private Long Id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Date yearsOfBirth;
    private String gender;

    private String role;

}
