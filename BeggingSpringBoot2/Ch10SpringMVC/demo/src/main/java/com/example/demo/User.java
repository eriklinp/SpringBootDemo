package com.example.demo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	@NotNull
	@Size(min=3, max=50)
	private String name;
	
	@NotNull
	@Email(message="{invalid.email}")
	private String email;
	
	@NotNull
	@Size(min=6, max=50)
	private String password;
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email +  "]";
	}
}
