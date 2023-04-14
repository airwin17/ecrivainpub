package com.ecrivainpub.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
@Entity(name="services")
public class ServiceItem {
	@Id
	@GeneratedValue
	private String idser;
	private String namecat;
	private String descser;
	private String prixser;
	@Lob
	@Column(columnDefinition="BLOB")
	private byte[] image;
	//default constructor
	public ServiceItem() {
		
	}
	//constructor
	public ServiceItem( String namecat, String descser, String prixser) {
		this.namecat = namecat;
		this.descser = descser;
		this.prixser = prixser;	
	}
	public String getIdser() {
		return idser;
	}
	public void setIdser(String idser) {
		this.idser = idser;
	}
	public String getNamecat() {
		return namecat;
	}
	public void setNamecat(String namecat) {
		this.namecat = namecat;
	}
	public String getDescser() {
		return descser;
	}
	public void setDescser(String descser) {
		this.descser = descser;
	}
	public String getPrixser() {
		return prixser;
	}
	public void setPrixser(String prixser) {
		this.prixser = prixser;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	
	
}