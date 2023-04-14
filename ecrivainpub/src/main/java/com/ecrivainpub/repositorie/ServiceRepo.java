package com.ecrivainpub.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecrivainpub.model.ServiceItem;

public interface ServiceRepo extends JpaRepository<ServiceItem,String>{
	
}
