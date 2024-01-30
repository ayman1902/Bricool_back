package ma.ac.emi.bricool.entities;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)

public abstract  class AppUser {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private Long Id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Date yearsOfBirth;
    private String gender;


    public abstract String getRole();
}
