package com.example.backend.entity;

import java.time.LocalDate;

public class User {
	
	private String id;
	private String name;
	private String email;
	private String status;
	private LocalDate joinedAt;	
	

	public User(String id, String name, String email, String status, LocalDate joinedAt)
	{
		this.id=id;
		this.email=email;
		this.name=name;
		this.status=status;
		this.joinedAt=joinedAt;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getJoinedAt() {
		return joinedAt;
	}
	public void setJoinedAt(LocalDate joinedAt) {
		this.joinedAt = joinedAt;
	}
	

}