package model;

public class Categorie {
	
	private String namecat;
	
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
