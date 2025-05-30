package org.example.projectmanagerapp.controller;

import org.example.projectmanagerapp.entity.Project;
import org.example.projectmanagerapp.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Projects", description = "Operations related to projects")
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService svc;
    public ProjectController(ProjectService svc) { this.svc = svc; }

    @Operation(summary = "Get all projects")
    @GetMapping
    public List<Project> getAllProjects() { return svc.getAllProjects(); }

    @Operation(summary = "Get project by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        return svc.getProjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new project")
    @PostMapping
    public Project createProject(@RequestBody Project p) { return svc.createProject(p); }

    @Operation(summary = "Update project by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project d) {
        return svc.updateProject(id, d)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete project by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        return svc.deleteProject(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}
