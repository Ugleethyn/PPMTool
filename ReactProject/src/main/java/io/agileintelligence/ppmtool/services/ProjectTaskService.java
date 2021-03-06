package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.domain.Backlog;
import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.domain.ProjectTask;
import io.agileintelligence.ppmtool.exceptions.ProjectNotFoundException;
import io.agileintelligence.ppmtool.repositories.BacklogRepository;
import io.agileintelligence.ppmtool.repositories.ProjectRepository;
import io.agileintelligence.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {
    @Autowired
    private BacklogRepository backlogRepository;
    @Autowired
    private ProjectTaskRepository projectTaskRepository;
    @Autowired
    private ProjectService projectService;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask, String username) {

        Backlog backlog = projectService.findProjectByIdentifier(projectIdentifier, username).getBacklog();
        projectTask.setBacklog(backlog);
        Integer backlogSequence = backlog.getPTSequence();
        backlogSequence++;
        backlog.setPTSequence(backlogSequence);
        projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);
        if (projectTask.getPriority() == null || projectTask.getPriority() == 0) {
            projectTask.setPriority(3);
        }
        if (projectTask.getStatus() == null || projectTask.getStatus() == "") {
            projectTask.setStatus("TO_DO");
        }
        return projectTaskRepository.save(projectTask);
    }

    public Iterable<ProjectTask> findBacklogById(String backlog_id, String username) {
        List<ProjectTask> listOfTasks = projectTaskRepository.findByProjectIdentifierOrderByPriority
                (projectService.findProjectByIdentifier(backlog_id, username).getProjectIdentifier());
        if (listOfTasks.size() == 0) {
            throw new ProjectNotFoundException("Project not Found or Project hasn't any Task");
        }
        return listOfTasks;
    }

    public ProjectTask findPTByProjectSequence(String backlog_id, String sequence, String username) {
        if (backlogRepository.findByProjectIdentifier
                (projectService.findProjectByIdentifier(backlog_id, username).getProjectIdentifier()) == null) {
            throw new ProjectNotFoundException("Project with id " + backlog_id + " does not exists");
        }
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(sequence);
        if (projectTask == null) {
            throw new ProjectNotFoundException("Project task with id " + sequence + " does not exists");
        }
        if (!projectTask.getProjectIdentifier().equals(backlog_id)) {
            throw new ProjectNotFoundException("Project task with id " + sequence + " does not exists in Project with id " + backlog_id);
        }
        return projectTask;
    }

    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlog_id, String sequence, String username) {
        ProjectTask projectTask = findPTByProjectSequence(backlog_id, sequence, username);
        projectTask = updatedTask;
        return projectTaskRepository.save(projectTask);
    }

    public void deleteProjectTaskBySequence(String backlog_id, String sequence, String username) {
        projectTaskRepository.delete(findPTByProjectSequence(backlog_id, sequence, username));
    }
}
