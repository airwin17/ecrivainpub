package com.ecrivainpub.control;


import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ecrivainpub.model.Categorie;
import com.ecrivainpub.model.ServiceItem;
import com.ecrivainpub.services.CategorieService;
import com.ecrivainpub.services.ServiceService;

@Controller
public class ControlAdmin {
	@Autowired
	private CategorieService categorieService;
	@Autowired
	private ServiceService sservice;
	@GetMapping("/admin")
	public String adminview(Model model) throws SQLException {
		model.addAttribute("categ", categorieService.findAll());
		return "admin";
		
	}
	@GetMapping("/admin/submitcat")
	public String addcat(@RequestParam("catname") String str) throws SQLException{
		Categorie categ=new Categorie(str);
		categorieService.save(categ);
		return "redirect:/admin"; 
		
		
	}
	@GetMapping("/admin/categorie")
	@ResponseBody
	public ResponseEntity<List<ServiceItem>> loadforadmin(@RequestParam("categorie") String str) throws SQLException {
		var ser=sservice.findAll();
		var mat=new LinkedList<ServiceItem>();
		for(int i=0;i<ser.size();i++) {
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
	@GetMapping("/admin/editcat")
	public String adminedit(
			@RequestParam("catname") String catname,
			@RequestParam("newname") String newname,
			@RequestParam("ree") String ree
			) {
		if(ree.equals("Supprimer")) {
			var serlist=sservice.findAll();
			var todelete=new LinkedList<ServiceItem>();
			for(int i=0;i<serlist.size();i++) {
				if(serlist.get(i).equals(catname)) {
					todelete.add(serlist.get(i));
				}
			}
			for(int i=0;i<todelete.size();i++) {
				sservice.deleteById(todelete.get(i).getIdser());
			}
			categorieService.deleteById(catname);
		}else
			categorieService.findById(catname).get().setCatname(newname);
		return "redirect:/admin";
		
}
	
@PostMapping("/admin/addservice")
public ResponseEntity<String> addservice(@RequestBody ServiceItem service) throws SQLException {
	sservice.seve(service);
	return new ResponseEntity<String>("succes", HttpStatus.OK);
}
@PostMapping(value="/admin/editservice")
public ResponseEntity<String> editservice(
		@RequestBody ServiceItem service,
		@RequestParam("action") String action
		) {
		if(action.equals("Modifier")) {
			if(service.getImage()!=null) {
				//sservice.updateImg(service.getIdser(), service.getDescser(), service.getPrixser(), service.getImage());
			}else
				sservice.updateNoImg(service.getIdser(), service.getDescser(), service.getPrixser());
			}else if(action.equals("Supprimer")) {
				sservice.deleteById(service.getIdser());
		}
	
	return new ResponseEntity<String>("succes",HttpStatus.OK);
}
}

