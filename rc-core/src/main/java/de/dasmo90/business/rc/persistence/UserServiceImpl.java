package de.dasmo90.business.rc.persistence;

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
