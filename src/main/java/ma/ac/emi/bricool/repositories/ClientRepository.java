package ma.ac.emi.bricool.repositories;

import ma.ac.emi.bricool.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {


}
