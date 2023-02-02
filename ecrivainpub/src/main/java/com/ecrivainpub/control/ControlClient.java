package com.ecrivainpub.control;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Categorie;
import model.Data;
import model.Service;
@Controller
public class ControlClient {
	String username=Data.databaseUsername;
	String password=Data.databasePassword;
	String databaseUrl=Data.databaseUrl;
	@GetMapping("/")
	public String defaultLoad() {
		return "redirect:/client";
	}
	@GetMapping("/client")
	public String homeview(Model model) throws SQLException {
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
		
		return "client";
		
	}
	
	@GetMapping("/client/categorie")
	@ResponseBody
	public ResponseEntity<List<Service>> loadforClient(@RequestParam("categorie") String str) throws SQLException, IOException {
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
	


}
