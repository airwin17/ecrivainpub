package com.ecrivainpub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class EcrivainPublicApplication {
	public static void main(String[] args) {
		//Data.databaseUsername="root";
		//Data.databasePassword="nonono";
//		Data.databaseUrl=args[0];
//		Data.databaseUsername=args[1];
//		Data.databasePassword=args[2];
		
		//Data.cloudSqlInstancename="ecrivain-public:europe-west9:root";
		//Data.socketFactory="com.google.cloud.sql.mysql.SocketFactory";
		//Data.dbname="servicemarseille";
		//Data.databaseUrl="jdbc:mysql://34.163.203.82/";
		//String cloudurl="jdbc:mysql:///"+Data.dbname+"?cloudSqlInstance="+Data.cloudSqlInstancename+"&socketFactory="+Data.socketFactory+"&user="+Data.databaseUsername+"&password="+Data.databasePassword;
		/*var runoncloud=false;
		if(runoncloud==true) {
			Data.url=cloudurl;
		}else {
			Data.url=Data.databaseUrl+"?user="+Data.databaseUsername+"&password="+Data.databasePassword;
		}
		Data.databaseUrl="jdbc:mysql://34.163.203.82/";
		Data.databaseUsername="root";
		Data.databasePassword="nonono";
		Data.cloudSqlInstancename="ecrivain-public:europe-west9:root";
		Data.socketFactory="com.google.cloud.sql.mysql.SocketFactory";
		*/
		
		
		SpringApplication.run(EcrivainPublicApplication.class, args);
		
	}
}
