package com.meetapp.appuser.dao;

import org.springframework.security.core.userdetails.User;
import com.meetapp.appuser.model.AppUser;

public interface AppUserDAO {

	public void insertUser(AppUser user);

	public AppUser getUser(String username);

	
}
