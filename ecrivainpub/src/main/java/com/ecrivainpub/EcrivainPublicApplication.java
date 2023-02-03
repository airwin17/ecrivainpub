package com.ecrivainpub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import model.Data;

@SpringBootApplication
public class EcrivainPublicApplication {
	
	public static void main(String[] args) {
//		Data.databaseUrl=args[0];
//		Data.databaseUsername=args[1];
//		Data.databasePassword=args[2];
		Data.databaseUrl="jdbc:mysql://34.163.203.82/";
		Data.databaseUsername="root";
		Data.databasePassword="nonono";
		SpringApplication.run(EcrivainPublicApplication.class, args);
		
	}

}
