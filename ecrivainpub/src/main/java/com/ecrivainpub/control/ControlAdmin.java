package com.ecrivainpub.control;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.cj.jdbc.Blob;

import model.Categorie;
import model.Data;
import model.Service;

@Controller
public class ControlAdmin {
	
	String username=Data.databaseUsername;
	String password=Data.databasePassword;
	String databaseUrl=Data.databaseUrl;
	
	

	@GetMapping("/admin")
	public String adminview(Model model) throws SQLException {
		List<Categorie> cats=new ArrayList<>();
		var cat =new LinkedList<Categorie>();
		Connection con=DriverManager.getConnection(databaseUrl,username,password);
		var st=con.createStatement();
		st.addBatch("use servicemarseille");
		st.executeBatch();
		var rs=st.executeQuery("select * from categorie");
		while (rs.next()) { 
			cat.add(new Categorie(rs.getString("namecat")));
		}
		model.addAttribute("categ", cat);
		
		return "admin";
		
	}
	@GetMapping("/admin/submitcat")
	
	public String addcat(@RequestParam("catname") String str) throws SQLException{
		
		Connection con=DriverManager.getConnection(databaseUrl,username,password);
		var st=con.createStatement();
		if(str.length()>0&&str.charAt(0)!=' ') {
			st.addBatch("use servicemarseille");
			st.addBatch("insert into categorie(namecat) values('"+str+"');");
			st.executeBatch();
		}
		
		return "redirect:/admin"; 
		
		
	}
	@GetMapping("/admin/categorie")
	@ResponseBody
	public ResponseEntity<List<Service>> loadforadmin(@RequestParam("categorie") String str) throws SQLException, IOException {
		var ser=new LinkedList<Service>();
		
		Connection con=DriverManager.getConnection(databaseUrl,username,password);
		var st=con.createStatement();
		st.addBatch("use servicemarseille");
		st.executeBatch();
		var rs=st.executeQuery("select * from services where namecat='"+str+"';");
		while(rs.next()) {
			//Blob blob=;
			Service serv=new Service(str,rs.getString("descser"),rs.getString("prixser"));
			serv.image=rs.getBlob("image").getBinaryStream().readAllBytes();
			serv.idser=rs.getString("idser");
			ser.add(serv);
		}
		if(ser.size()>0) {
			
			return new ResponseEntity<List<Service>>(ser,HttpStatus.OK);
		}else {
			
			return new ResponseEntity<List<Service>>(ser,HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/admin/editcat")
	public String adminedit(@RequestParam("catname") String catname,@RequestParam("newname") String newname,@RequestParam("ree") String ree) throws SQLException {
		Connection con=DriverManager.getConnection(databaseUrl,username,password);
		var st=con.createStatement();
		System.out.println(catname);
		System.out.println(newname);
		System.out.println(ree);
		if(ree.equals("Supprimer")) {
			System.out.println(4);
			st.addBatch("use servicemarseille");
			st.addBatch("delete from services where namecat='"+catname+"'");
			st.addBatch("delete from categorie where namecat='"+catname+"'");
			
		}else if(ree.equals("Renommer")&& ree.length()>0) {
			System.out.println(5);
			st.addBatch("use servicemarseille");
			st.addBatch("UPDATE categorie set namecat='"+newname+"' where namecat='"+catname+"';");
			
		}
		st.executeBatch();
		return "redirect:/admin";
		
	}
	
@PostMapping(value="/admin/addservice", consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
public ResponseEntity<String> addservice(@ModelAttribute Service service,@RequestPart MultipartFile img) throws SQLException, IOException {
	
	
	
	service.img=img;
	if (service.prixser.equals("")==false&&service.namecat.equals("")==false&&service.descser.equals("")==false) {
		Connection con = DriverManager.getConnection(databaseUrl, username, password);
		String query = "insert into servicemarseille.services(namecat,descser,prixser,image) values(?,?,?,?)";
		var st = con.prepareStatement(query);
		st.setString(1, service.namecat);
		st.setString(2, service.descser);
		st.setString(3, service.prixser);
		st.setBlob(4, service.img.getInputStream());
		
		st.execute();
	}
	return new ResponseEntity<String>("succes", HttpStatus.OK);
}
@PostMapping(value="/admin/editservice")
public ResponseEntity<String> editservice(@RequestBody Service service,@RequestParam("action") String action) throws SQLException, IOException{
	

	if(service.prixser.equals("")==false&&service.namecat.equals("")==false&&service.descser.equals("")==false) {
		Connection con = DriverManager.getConnection(databaseUrl, username, password);
		if(action.equals("Modifier")) {
			if(service.image!=null) {
				var st=con.prepareStatement("update servicemarseille.services set descser=?, prixser=? ,image=? where idser=?;");
				st.setString(1, service.descser);
				st.setString(2, service.prixser);
				st.setBlob(3 ,new ByteArrayInputStream(service.image));
				st.setString(4, service.idser);
				st.execute();
			
			}else {
					var st=con.prepareStatement("update servicemarseille.services set descser=?, prixser=? where idser=?;");
					st.setString(1, service.descser);
					st.setString(2, service.prixser);
					st.setString(3, service.idser);
					st.execute();
				}
			}else if(action.equals("Supprimer")) {
				var st=con.prepareStatement("delete from servicemarseille.services where idser=?");
				st.setString(1, service.idser);
				st.execute();
		}
	}
	return new ResponseEntity<String>("succes",HttpStatus.OK);
}
}

