package com.startuppoc.usermanage.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.startuppoc.usermanage.model.Project;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project,Long> {

}
