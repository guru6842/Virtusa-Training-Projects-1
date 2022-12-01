package com.virtusa.dto;

public class TenantDetail {
	private String firstName;
	private String lastName;
	private String phoneNo;
	private String email;
	private String aadharNo;
	private int flatNo;
	private String date;
	public TenantDetail() {
		super();
	}
	
	public TenantDetail(String firstName, String lastName, String phoneNo, String email, String aadharNo, int flatNo) 
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNo = phoneNo;
		this.email = email;
		this.aadharNo = aadharNo;
		this.flatNo = flatNo;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public int getFlatNo() {
		return flatNo;
	}
	public void setFlatNo(int flatNo) {
		this.flatNo = flatNo;
	}
	public String getDate() {
		return date;
	}

}
