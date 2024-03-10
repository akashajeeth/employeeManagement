package com.gl.test.EmployeePostmanProj;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.test.EmployeePostmanProj.Model.Employee;
import com.gl.test.EmployeePostmanProj.repositoryandService.AppUserService;
import com.gl.test.EmployeePostmanProj.repositoryandService.EmployeeService;

@RestController
public class ApiEmployee {
	@Autowired
	EmployeeService service;
	
	@Autowired
	AppUserService appUserService;
	
	@PostMapping("/accept-page")
	public String acceptPage(Authentication authentication,SecurityContextHolder auth) {
		
		//String acceptedRole="ROLE_admin"; //for inmemory
		String acceptedRole="admin";
		boolean roleFound=false;
		
		//who is currently loggin in
		System.out.println("Current login by : " + authentication.getName());
		
		//find the role of the person who have logged
		Collection<?extends GrantedAuthority> grantedRoles=auth.getContext().getAuthentication().getAuthorities(); //get the roles(S) mapped with the user
		
		for(int i=0;i<grantedRoles.size();i++) {
			String role=grantedRoles.toArray()[i].toString();
			System.out.println("my role : " + role);
			
			if(role.equalsIgnoreCase(acceptedRole)) {
				roleFound=true;
			}
		}
		if(roleFound) {
			return "user successfully added";
		}
		else {	
			
			return "403";
		}
	}
	
	//add employee method
	@PostMapping("api/addEmployee")
	public String addEmployees(@RequestParam int id,@RequestParam String firstName,@RequestParam String lastName,@RequestParam String email,Authentication authentication,SecurityContextHolder auth) {
		
		String acceptedRole="admin";
		boolean roleFound=false;
		
		//who is currently loggin in
		System.out.println("Current login by : " + authentication.getName());
		
		//find the role of the person who have logged
		Collection<?extends GrantedAuthority> grantedRoles=auth.getContext().getAuthentication().getAuthorities(); //get the roles(S) mapped with the user
		
		for(int i=0;i<grantedRoles.size();i++) {
			String role=grantedRoles.toArray()[i].toString();
			System.out.println("my role : " + role);
			
			if(role.equalsIgnoreCase(acceptedRole)) {
				roleFound=true;
			}
		}
		if(roleFound) {
			if(service.getById(id)!=null) {
				return "Dublicate Entry";
			}
			Employee e1=new Employee(id, firstName, lastName, email);
			service.add(e1);
			return "Employee added";
		}
		return "user cannot be added";
	}
	
	//update Employee method
	@PutMapping("api/updateEmployee")
	public String update(@RequestParam int id,@RequestParam String firstName,@RequestParam String lastName,@RequestParam String email,Authentication authentication,SecurityContextHolder auth) {
		String acceptedRole="admin";
		boolean roleFound=false;
		
		//who is currently loggin in
		System.out.println("Current login by : " + authentication.getName());
		
		//find the role of the person who have logged
		Collection<?extends GrantedAuthority> grantedRoles=auth.getContext().getAuthentication().getAuthorities(); //get the roles(S) mapped with the user
		
		for(int i=0;i<grantedRoles.size();i++) {
			String role=grantedRoles.toArray()[i].toString();
			System.out.println("my role : " + role);
			
			if(role.equalsIgnoreCase(acceptedRole)) {
				roleFound=true;
			}
		}
		if(roleFound) {
			Employee u1=new Employee(id, firstName, lastName, email);
			service.update(u1);
			return "Employee Updated";
		}
		
		return "User cannot update";		
	}
	
	//show all Employee
	@GetMapping("api/getAllEmployee")
	public List<Employee> getAll(){		
		return service.getAll();
	}
	
	//get by id method
	@GetMapping("api/getByIdEmployee")
	public  Employee getByIdEmp(@RequestParam int id) {
		return service.getById(id);
	}
	
	//delete an employee using -- id
	@DeleteMapping("api/deleteEmployee")
	public  String delete(@RequestParam int id,Authentication authentication,SecurityContextHolder auth) {
		String acceptedRole="admin";
		boolean roleFound=false;
		
		//who is currently loggin in
		System.out.println("Current login by : " + authentication.getName());
		
		//find the role of the person who have logged
		Collection<?extends GrantedAuthority> grantedRoles=auth.getContext().getAuthentication().getAuthorities(); //get the roles(S) mapped with the user
		
		for(int i=0;i<grantedRoles.size();i++) {
			String role=grantedRoles.toArray()[i].toString();
			System.out.println("my role : " + role);
			
			if(role.equalsIgnoreCase(acceptedRole)) {
				roleFound=true;
			}
		}
		if(roleFound) {
			Employee d1=new Employee(id, "", "", "");
			service.delete(d1);
			return "Employee Deleted";
		}
		return "User cannot delete";
	}
	
	//filter method
	@GetMapping("api/filter")
	public List<Employee> filter(@RequestParam String firstName){
		return service.filter(firstName);
	}
	
	//sorting by name in assending order
	@GetMapping("api/soring")
	public List<Employee> soringByName(@RequestParam String columnName,@RequestParam Direction direction ){
		return service.getBySortOnly(columnName, Direction.ASC);
	}

}
