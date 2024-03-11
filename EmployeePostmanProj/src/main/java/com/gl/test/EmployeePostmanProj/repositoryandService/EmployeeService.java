package com.gl.test.EmployeePostmanProj.repositoryandService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gl.test.EmployeePostmanProj.Model.Employee;

@Service
public class EmployeeService {
	@Autowired
	EmployeeRepo repo;
	
	public void add(Employee employee) {
		repo.save(employee);
	}
	public void update(Employee employee) {
		repo.save(employee);
	}
	public List<Employee> getAll(){
		return repo.findAll();
	}
	public void delete(Employee employee) {
		repo.delete(employee);
	}
	public Employee findById(int id) {
		return repo.findById(id).get();
	}
	
	public Employee getById(int id) {
		Optional<Employee> emp=repo.findById(id);
		Employee temp=null;
		if(!emp.isEmpty()) {
			temp=emp.get();
		}
		return temp;
	}
	
	//filter
		public List<Employee> filter(String searchkey){
			
			//1.create a dummy object based on the searchKey
			Employee dummy=new Employee();
			dummy.setFirstName(searchkey);
			
			//2.Create example jpa-where
			ExampleMatcher em=ExampleMatcher.matching().withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.contains()).withIgnorePaths("id","lastname","email");
			
			//3. combining Dummy with where
			Example<Employee> example=Example.of(dummy,em);
			//System.out.println("here");
			return repo.findAll(example);
		}
		
		//only sorting
		public List<Employee> getBySortOnly(String columns,Direction direction){
			
			return repo.findAll(Sort.by(direction, columns));
		}

}
