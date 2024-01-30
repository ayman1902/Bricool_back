package ma.ac.emi.bricool.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@Slf4j
public class JwtTokenService {

    private static final long JWT_TOKEN_VALIDITY = 400 * 60 * 1000; // 100 minutes

    private final Algorithm hmac512;
    private final JWTVerifier verifier;

    public JwtTokenService(@Value("${jwt.secret}") final String secret) {
        this.hmac512 = Algorithm.HMAC512(secret);
        this.verifier = JWT.require(this.hmac512).build();
    }

    public String generateToken(final UserDetails userDetails) {
        String role = userDetails.getAuthorities()
                .stream()
                .findFirst() // Puisque chaque utilisateur a un seul rôle, on prend le premier.
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .orElse(null); // Gérez le cas où aucun rôle n'est trouvé comme vous le souhaitez.

        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withClaim("role", role)
                .withExpiresAt(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .sign(this.hmac512);
    }

    public String validateTokenAndGetUsername(final String token) {
        try {
            return verifier.verify(token).getSubject();
        } catch (final JWTVerificationException verificationEx) {
            log.warn("token invalid: {}", verificationEx.getMessage());
            return null;
        }
    }

}
