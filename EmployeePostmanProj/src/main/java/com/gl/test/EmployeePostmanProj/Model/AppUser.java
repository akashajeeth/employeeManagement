package com.gl.test.EmployeePostmanProj.Model;

import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUser {
	@Id
	private int id;
	@Column(unique = true)
	private String name;
	private String password;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="user_roles",joinColumns = @JoinColumn(name="user_id"))
	private Set<String> roles;

}
