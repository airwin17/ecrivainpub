package com.ecrivainpub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import com.ecrivainpub.model.ServiceItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecrivainpub.repositorie.ServiceRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

@Service
@Transactional
public class ServiceService {
@Autowired
private ServiceRepo serviceRepo;
EntityManagerFactory emf=Persistence.createEntityManagerFactory("Ecl");
EntityManager em=emf.createEntityManager();
EntityTransaction transaction = em.getTransaction();
public void seve(ServiceItem service){
	
	transaction.begin();
	String sql="INSERT INTO services (descser, prixser,image,namecat) VALUES (?,?,?,?)";
	Query query=em.createNativeQuery(sql);
	query.setParameter(1, service.getDescser());
	query.setParameter(2, service.getPrixser());
	query.setParameter(3, service.getImage());
	query.setParameter(4, service.getNamecat());
	//query.setParameter(4, service.getIdser());
	query.executeUpdate();
	transaction.commit();
}
public Optional<ServiceItem> findById(String str){
	return serviceRepo.findById(str);
}
public List<ServiceItem> findAll(){
	return serviceRepo.findAll();
}
public void deleteById(String str) {
	transaction.begin();
	Query query=em.createNativeQuery("DELETE FROM services WHERE idser="+str);
	query.executeUpdate();
	transaction.commit();
}


public void updateNoImg(String idser, String descser,String prixser) {
	
	transaction.begin();
	String sql="UPDATE services SET descser=?, prixser=? WHERE idser=?";
	Query query=em.createNativeQuery(sql);
	query.setParameter(1, descser);
	query.setParameter(2, prixser);
	query.setParameter(3, idser);
	query.executeUpdate();
	transaction.commit();
}
/*@Query("UPDATE ServiceItem s SET s.descser=?2, s.prixser=?3,s.image=?4 WHERE s.idser=?1")
public void updateImg(String idser, String descser,String prixser,byte[] img) {
}*/
}
