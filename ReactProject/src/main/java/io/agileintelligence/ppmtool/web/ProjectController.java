package io.agileintelligence.ppmtool.web;

import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.services.MapValidationErrorService;
import io.agileintelligence.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult bindingResult, Principal principal) {
        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationErrorService(bindingResult);
        if (errorMap != null) return errorMap;
        return new ResponseEntity<Project>(projectService.saveOrUpdateProject(project, principal.getName()), HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId, Principal principal) {
        return new ResponseEntity<Project>(projectService.findProjectByIdentifier(projectId, principal.getName()), HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects(Principal principal) {
        return projectService.findAllProjects(principal.getName());
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId, Principal principal) {
        projectService.deleteProjectByIdentifier(projectId,principal.getName());
        return new ResponseEntity<String>("Project with ID : '" + projectId + "' was deleted", HttpStatus.OK);
    }
}
