package ma.ac.emi.bricool.DTO.incoming_request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
@AllArgsConstructor
public class RegistrationRequest {

    @Size(max = 255)
    private String firstName;

    @Size(max = 255)
    private String lastName;

    @Size(max = 255)
    private String email;

    private String phoneNumber;

    @NotNull
    @Size(max = 255)
    private String password;

    private Date yearsOfBirth;
    private String gender;

}
