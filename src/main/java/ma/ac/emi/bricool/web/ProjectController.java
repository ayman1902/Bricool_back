package ma.ac.emi.bricool.web;

import ma.ac.emi.bricool.entities.Project;
import ma.ac.emi.bricool.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sellers/{sellerId}/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // Get all projects of a seller
    @GetMapping
    public List<Project> getAllProjectsBySellerId(@PathVariable Long sellerId) {
        return projectService.getAllProjectsBySellerId(sellerId);
    }

    // Get a specific project of a seller by project ID
    @GetMapping("/{projectId}")
    public Project getProjectById(@PathVariable Long sellerId, @PathVariable Long projectId) {
        return projectService.getProjectById(sellerId, projectId);
    }

    // Create a new project for a seller
    @PostMapping
    public Project createProject(@PathVariable Long sellerId, @RequestBody Project project) {
        return projectService.createProject(sellerId, project);
    }

    // Update a project of a seller by project ID
    @PutMapping("/{projectId}")
    public Project updateProject(@PathVariable Long sellerId, @PathVariable Long projectId, @RequestBody Project updatedProject) {
        return projectService.updateProject(sellerId, projectId, updatedProject);
    }

    // Delete a project of a seller by project ID
    @DeleteMapping("/{projectId}")
    public boolean deleteProject(@PathVariable Long sellerId, @PathVariable Long projectId) {
        return projectService.deleteProject(sellerId, projectId);
    }
}
