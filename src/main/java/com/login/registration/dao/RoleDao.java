package com.login.registration.dao;

import com.login.registration.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
