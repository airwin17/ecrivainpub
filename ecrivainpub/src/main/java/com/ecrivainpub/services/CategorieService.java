package com.ecrivainpub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecrivainpub.model.Categorie;
import com.ecrivainpub.repositorie.CategorieRepo;

@Service
public class CategorieService {
 @Autowired
 private CategorieRepo categorieRepo;
 //creat
 public Categorie save(Categorie categorie) {
	return categorieRepo.save(categorie);
 }
 //read
 public Optional<Categorie> findById(String str) {
	return categorieRepo.findById(str);
 }
 //readall
 public List<Categorie> findAll(){
	 return categorieRepo.findAll();
 }
 //remove
 public void deleteById(String str) {
	 categorieRepo.deleteById(str);
 }
}
