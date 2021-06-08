package io.agileintelligence.ppmtool.repositories;

import io.agileintelligence.ppmtool.domain.Backlog;
import io.agileintelligence.ppmtool.domain.Project;
import io.agileintelligence.ppmtool.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog,Long> {

    Backlog findByProjectIdentifier(String projectIdentifier);

}
