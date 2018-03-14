package kr.co.bitmusic.domain;

import java.util.Date;

public class User {
	private String id; 
	private String password; 
	private String passwordHint; 
	private String name; 
	private int age; 
	private String gender; 
	private String email; 
	private Date joinDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordHint() {
		return passwordHint;
	}
	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", passwordHint=" + passwordHint + ", name=" + name
				+ ", age=" + age + ", gender=" + gender + ", email=" + email + ", joinDate=" + joinDate + "]";
	}
	
}
