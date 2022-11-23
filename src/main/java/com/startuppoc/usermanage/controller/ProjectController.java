package com.startuppoc.usermanage.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.startuppoc.usermanage.model.Project;
import com.startuppoc.usermanage.repository.ProjectRepository;
import com.startuppoc.usermanage.repository.RoleRepository;
import com.startuppoc.usermanage.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/project")
public class ProjectController {
	@Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ProjectRepository projectRepository;
    
//Project routes
	@GetMapping("/getprojects")
	public List<Project> findAllProjects(){
		return projectRepository.findAll();
	};
	
	@PostMapping("/postproject")
	public Project saveProject(@Valid @NotNull @RequestBody Project project ){
		return projectRepository.save(project);
	};
	
	@PutMapping("/changeproject")
	public Project updateProject(@Valid @NotNull @RequestBody Project developer ){
		return projectRepository.save(developer);
	};
	
	@DeleteMapping(value= "/deleteproject/{id}")
	public void deleteProject(@PathVariable long id ){
		projectRepository.deleteById(id);
	};
}
