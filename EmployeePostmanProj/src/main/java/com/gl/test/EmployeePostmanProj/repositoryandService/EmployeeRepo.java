package com.gl.test.EmployeePostmanProj.repositoryandService;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.test.EmployeePostmanProj.Model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

}
