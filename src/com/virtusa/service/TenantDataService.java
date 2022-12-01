package com.virtusa.service;

import com.virtusa.dto.TenantDetail;

public interface TenantDataService {
	public void listOfTenants();
	public boolean addNewTenant(TenantDetail obj);
	public boolean searchTenant(int flatNo);
	public boolean deleteTenant(int flatNo);
	public TenantDetail tenantInput();
	public boolean editTenant(int flatNo);
	public boolean usernamepwd(int flatNo);
}
