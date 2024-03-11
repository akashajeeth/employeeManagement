package com.gl.test.EmployeePostmanProj.repositoryandService;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.test.EmployeePostmanProj.Model.AppUser;

public interface AppUserRepo extends JpaRepository<AppUser, Integer>{
	
	Optional<AppUser> findByName(String name);

}
