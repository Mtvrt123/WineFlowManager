package ita.ws.winestorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//import org.springframework.context.annotation.Bean;

//import ita.ws.winestorage.dao.VinoRepository;

@SpringBootApplication
//@EnableMongoRepositories
public class WinestorageApplication {

	//@Autowired
	//VinoRepository vinoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(WinestorageApplication.class, args);
	}

}
