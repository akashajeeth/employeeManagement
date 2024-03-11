package com.gl.test.EmployeePostmanProj.repositoryandService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gl.test.EmployeePostmanProj.Model.AppUser;


@Service
public class AppUserService implements UserDetailsService {
	@Autowired
	AppUserRepo repo;
	
	public void add(AppUser user) {
		repo.save(user);
	}
	public List<AppUser> getAll(){
		return repo.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
        Optional<AppUser> appUser=repo.findByName(username);
		
		//we are converting the roles from db to grantedAuth of userDetailsService
		Set<GrantedAuthority> grantedAuthorities=new HashSet<>();
		
		for(String temp: appUser.get().getRoles()) {
			GrantedAuthority ga=new SimpleGrantedAuthority(temp);
			grantedAuthorities.add(ga);
		}
		//converting appUser to user from user security
		User user=new User(username,appUser.get().getPassword(),grantedAuthorities);
		return user;
	}
	

}
