package ma.ac.emi.bricool.repositories;

import ma.ac.emi.bricool.entities.Project;

import org.springframework.data.jpa.repository.JpaRepository;



public interface ProjectRepository extends JpaRepository<Project, Long> {
}
