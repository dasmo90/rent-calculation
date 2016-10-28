package de.dasmo90.business.rc.service;

import de.dasmo90.business.rc.api.User;

public interface UserService {

	User fetchUserByName(String name);
}
