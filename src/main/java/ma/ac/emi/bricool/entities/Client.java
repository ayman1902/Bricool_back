package ma.ac.emi.bricool.entities;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Client{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ClientId;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Date yearsOfBirth;
    private String gender;


}
