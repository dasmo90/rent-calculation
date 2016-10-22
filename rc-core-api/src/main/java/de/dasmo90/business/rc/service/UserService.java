package de.dasmo90.business.rc.service;

import de.dasmo90.business.rc.model.User;

public interface UserService {

	User fetchUserByName(String name);
}
