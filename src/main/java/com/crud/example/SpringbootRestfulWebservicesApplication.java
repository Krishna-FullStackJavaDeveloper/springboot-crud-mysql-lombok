package com.crud.example;

import com.crud.example.service.URLFilesStorageService;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//We need to run init() method of FilesStorageService (and also deleteAll() if necessary).

@SpringBootApplication
public class SpringbootRestfulWebservicesApplication implements CommandLineRunner {

	@Resource
	URLFilesStorageService urlFilesStorageService;


	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfulWebservicesApplication.class, args);
	}


	@Override
	public void run(String... arg) throws Exception{
	//	urlFilesStorageService.deleteAll();
		urlFilesStorageService.init();
	}
}
