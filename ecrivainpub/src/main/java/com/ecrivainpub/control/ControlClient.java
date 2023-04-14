package com.ecrivainpub.control;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.ecrivainpub.model.*;
import com.ecrivainpub.services.CategorieService;
import com.ecrivainpub.services.ServiceService;
@Controller
public class ControlClient {
	@Autowired
	private CategorieService categorieService;
	@Autowired
	private ServiceService sservice;
	String username=Data.databaseUsername;
	String password=Data.databasePassword;
	String databaseUrl=Data.databaseUrl;
	@GetMapping("/")
	public String defaultLoad() {
		return "redirect:/client";
	}
	@GetMapping("/client")
	public String homeview(Model model)  {
		model.addAttribute("categ", categorieService.findAll());
		return "client";
		
	}
	
	
	@GetMapping("/client/categorie")
	@ResponseBody
	public ResponseEntity<List<ServiceItem>> loadforClient(@RequestParam("categorie") String str) throws SQLException, IOException {
		
		var ser=sservice.findAll();
		var mat=new LinkedList<ServiceItem>();
		for(int i=0;i<=ser.size()-1;i++) {
			if(ser.get(i).getNamecat().equals(str)==true) {
				mat.add(ser.get(i));
			}
		}
		if(mat.size()>0) {
			return new ResponseEntity<List<ServiceItem>>(mat,HttpStatus.OK);
		}else {
			return new ResponseEntity<List<ServiceItem>>(mat,HttpStatus.NOT_FOUND);
		}
	}
	


}
