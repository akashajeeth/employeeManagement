package com.gl.test.EmployeePostmanProj;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gl.test.EmployeePostmanProj.Model.AppUser;
import com.gl.test.EmployeePostmanProj.repositoryandService.AppUserService;

@SpringBootApplication
public class EmployeePostmanProjApplication implements CommandLineRunner{
	@Autowired
	AppUserService appUserService;

	public static void main(String[] args) {
		SpringApplication.run(EmployeePostmanProjApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Set<String> authAdmin=new HashSet<>();
		authAdmin.add("admin");
		
		Set<String> authUser=new HashSet<>();
		authUser.add("temp");
		
		//Create encode object
		PasswordEncoder en=new BCryptPasswordEncoder();
		
		AppUser appAdmin=new AppUser(1,"admin",en.encode("adminPassword"),authAdmin);
		appUserService.add(appAdmin);
		
		AppUser appUser=new AppUser(2,"temp",en.encode("12345"),authUser);
		appUserService.add(appUser);
		
		
	}			

} 
