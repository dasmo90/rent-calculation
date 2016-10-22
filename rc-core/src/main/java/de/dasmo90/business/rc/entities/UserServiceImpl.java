package de.dasmo90.business.rc.entities;

import de.dasmo90.business.rc.model.User;
import de.dasmo90.business.rc.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public User fetchUserByName(String name) {
		return null;
	}

}
