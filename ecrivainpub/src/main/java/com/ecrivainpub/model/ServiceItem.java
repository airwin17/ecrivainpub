package model;

import java.awt.Image;
import java.io.File;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data
public class Service {
	public String idser;
	public String namecat;
	public String descser;
	public String prixser;
	public MultipartFile img;
	public byte[] image;
	
	
	public Service( String namecat, String descser, String prixser) {
		
		this.namecat = namecat;
		this.descser = descser;
		this.prixser = prixser;
		
	}
	
	
	
}