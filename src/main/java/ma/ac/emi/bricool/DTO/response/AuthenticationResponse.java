package ma.ac.emi.bricool.DTO.response;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ma.ac.emi.bricool.DTO.user_management.AppUserDTO;


@Getter
@Setter
@ToString
public class AuthenticationResponse {
    private AppUserDTO user;

    private String accessToken;
}
