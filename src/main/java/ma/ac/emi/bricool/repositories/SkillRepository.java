package ma.ac.emi.bricool.repositories;

import ma.ac.emi.bricool.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {


}
