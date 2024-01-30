package ma.ac.emi.bricool.DTO.user_management;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
public class ClientDTO {

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
