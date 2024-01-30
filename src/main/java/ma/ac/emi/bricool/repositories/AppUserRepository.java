package ma.ac.emi.bricool.repositories;

import ma.ac.emi.bricool.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long>{
    AppUser findByEmailIgnoreCase(String username);
}
