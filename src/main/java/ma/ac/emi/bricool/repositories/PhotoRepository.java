package ma.ac.emi.bricool.repositories;

import ma.ac.emi.bricool.entities.Photo;
import ma.ac.emi.bricool.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
