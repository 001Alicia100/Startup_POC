package com.startuppoc.usermanage.model;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "project")
public class Project {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;
    
    @NotBlank
    private String description;

    private ArrayList<Long> managers;

    private ArrayList<Long> users;

    private Date startingDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Long> getManagers() {
		return managers;
	}

	public void setManagers(ArrayList<Long> managers) {
		this.managers = managers;
	}

	public ArrayList<Long> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<Long> users) {
		this.users = users;
	}

	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}
}
