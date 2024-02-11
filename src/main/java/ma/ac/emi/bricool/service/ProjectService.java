package ma.ac.emi.bricool.service;

import ma.ac.emi.bricool.entities.Project;
import ma.ac.emi.bricool.entities.Seller;
import ma.ac.emi.bricool.repositories.ProjectRepository;
import ma.ac.emi.bricool.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final SellerRepository sellerRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, SellerRepository sellerRepository) {
        this.projectRepository = projectRepository;
        this.sellerRepository = sellerRepository;
    }


    // Create a new project for a seller
    public Project createProject(Long sellerId, Project project) {
        Seller seller = sellerRepository.findById(sellerId).orElse(null);
        if (seller != null) {
            project.setSeller(seller);
            return projectRepository.save(project);
        }
        return null;
    }

    // Get all projects of a seller
    public List<Project> getAllProjectsBySellerId(Long sellerId) {
        Seller seller = sellerRepository.findById(sellerId).orElse(null);
        return (seller != null) ? seller.getProjects() : Collections.emptyList();
    }

    // Get a specific project of a seller by project ID
    public Project getProjectById(Long sellerId, Long projectId) {
        Seller seller = sellerRepository.findById(sellerId).orElse(null);
        if (seller != null) {
            return seller.getProjects().stream()
                    .filter(project -> project.getProjectId().equals(projectId))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    // Update a project of a seller by project ID
    public Project updateProject(Long sellerId, Long projectId, Project updatedProject) {
        Seller seller = sellerRepository.findById(sellerId).orElse(null);
        if (seller != null) {
            List<Project> projects = seller.getProjects();
            for (Project project : projects) {
                if (project.getProjectId().equals(projectId)) {
                    // Update fields based on your requirements
                    project.setProjectName(updatedProject.getProjectName());
                    project.setDescription(updatedProject.getDescription());
                    project.setStartDate(updatedProject.getStartDate());
                    project.setEndDate(updatedProject.getEndDate());
                    project.setBudget(updatedProject.getBudget());
                    project.setStatus(updatedProject.getStatus());
                    project.setServiceType(updatedProject.getServiceType());
                    project.setLocation(updatedProject.getLocation());
                    project.setBookingAvailability(updatedProject.getBookingAvailability());
                    // Update additional fields
                    // ...

                    return projectRepository.save(project);
                }
            }
        }
        return null;
    }

    // Delete a project of a seller by project ID
    public boolean deleteProject(Long sellerId, Long projectId) {
        Seller seller = sellerRepository.findById(sellerId).orElse(null);
        if (seller != null) {
            List<Project> projects = seller.getProjects();
            for (Project project : projects) {
                if (project.getProjectId().equals(projectId)) {
                    projectRepository.deleteById(projectId);
                    return true;
                }
            }
        }
        return false;
    }
}
