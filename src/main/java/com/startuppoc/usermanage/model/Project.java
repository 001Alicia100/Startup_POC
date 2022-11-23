package com.startuppoc.usermanage.model;
import java.util.ArrayList;

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
    private String desc_projet;
    
    @NotBlank
    private String desc_entreprise;
    
    @NotBlank
    private String annee;
    
    private String lien_site;
    
    @NotBlank
    private String categorie;

    private ArrayList<String> listTech;
    
    @Size(max = 65)
    private String photos;

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

	public String getDesc_projet() {
		return desc_projet;
	}

	public void setDesc_projet(String desc_projet) {
		this.desc_projet = desc_projet;
	}

	public String getDesc_entreprise() {
		return desc_entreprise;
	}

	public void setDesc_entreprise(String desc_entreprise) {
		this.desc_entreprise = desc_entreprise;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public String getLien_site() {
		return lien_site;
	}

	public void setLien_site(String lien_site) {
		this.lien_site = lien_site;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}  
}
