package com.ecrivainpub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
public class Categorie {
	@Id
	private String namecat;
	
	public Categorie() {
		
	}
	public Categorie(String catname) {
		this.namecat = catname;
	}
	public String getCatname() {
		return namecat;
	}
	public void setCatname(String catname) {
		this.namecat = catname;
	}

}
