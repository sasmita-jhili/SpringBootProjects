package com.app;

import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.app.entity.ClassInfo;
import com.app.entity.ClassSubject;
import com.app.repo.ClassInfoRepository;
import com.app.repo.ClassSubjectRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

//import aj.org.objectweb.asm.TypeReference;
import io.jsonwebtoken.io.IOException;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title="student app",version="2.0",description="studentapp"))
public class StudentManagementServiceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementServiceAppApplication.class, args);
	}

	 @Bean
	    CommandLineRunner runner( ClassInfoRepository repo,
	    		ClassSubjectRepository subRepo) {
	        return args -> {
	            // read json and write to db
	            ObjectMapper mapper = new ObjectMapper();
	            TypeReference<List<ClassInfo>> typeReference = new TypeReference<List<ClassInfo>>(){};
	            TypeReference<List<ClassSubject>> typeReference1 = new TypeReference<List<ClassSubject>>(){};
	            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/classinfo.json");
	            InputStream inputStream1 = TypeReference.class.getResourceAsStream("/json/classsubject.json");
	            
	            try {
	                List<ClassInfo> classinfo = mapper.readValue(inputStream,typeReference);
	                repo.saveAll(classinfo);
	                System.out.println("ClassInfo Saved!");
	            } catch (IOException e){
	                System.out.println("Unable to ClassInfo: " + e.getMessage());
	            }
	            try {
	                List<ClassSubject> classSubject = mapper.readValue(inputStream1,typeReference1);
	                subRepo.saveAll(classSubject);
	                System.out.println("classSubject Saved!");
	            } catch (IOException e){
	                System.out.println("Unable to save users: " + e.getMessage());
	            }
	            
	        };
	    }
}
