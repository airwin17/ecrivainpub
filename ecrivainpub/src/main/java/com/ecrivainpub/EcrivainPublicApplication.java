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
		Data.databaseUrl="jdbc:mysql://10.27.160.4/";
		Data.databaseUsername="root";
		Data.databasePassword="nonono";
		Data.cloudSqlInstancename="ecrivain-public:europe-west9:root";
		Data.socketFactory="com.google.cloud.sql.mysql.SocketFactory";
		SpringApplication.run(EcrivainPublicApplication.class, args);
		
	}

}
