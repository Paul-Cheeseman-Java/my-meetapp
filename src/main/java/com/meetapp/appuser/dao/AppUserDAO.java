package com.meetapp.appuser.dao;

import com.meetapp.appuser.model.AppUser;

public interface AppUserDAO {

	public void insertUser(AppUser user);

	public AppUser getUser(String username);

	
}
