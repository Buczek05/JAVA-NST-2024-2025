package com.example.projectmanagerapp.controller;

import com.example.projectmanagerapp.entity.Projects;
import com.example.projectmanagerapp.service.ProjectService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@Tag(name = "Project Management", description = "APIs for managing projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    @Operation(summary = "Get all projects", description = "Retrieve a list of all projects")
    public List<Projects> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get project by ID", description = "Retrieve a project by ID")
    public ResponseEntity<Projects> getProjectById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new project", description = "Create a new project with the provided details")
    public ResponseEntity<Projects> addProjekt(@RequestBody Projects projects) {
        Projects created = projectService.createProject(projects);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing project", description = "Update the details of an existing project")
    public ResponseEntity<Projects> updateProject(@PathVariable Long id,
                                                  @RequestBody Projects projectData) {
        Projects updated = projectService.updateProject(id, projectData);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a project", description = "Delete a project by its ID")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{projectId}/users")
    @Operation(summary = "Assign a user to a project",
            description = "Assign a user to a project by their IDs")
    public ResponseEntity<Void> assignUserToProject(
            @PathVariable Long projectId,
            @RequestParam Long userId) {
        projectService.assignUserToProject(projectId, userId);
        return ResponseEntity.ok().build();
    }
}
