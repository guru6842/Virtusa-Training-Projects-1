package com.virtusa.authorization;

public interface IAuthorization {
	public boolean adminAuthorization(String userName,String password);
	public int userAuthorization(String userName,String password);

}
