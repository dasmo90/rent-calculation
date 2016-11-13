package de.dasmo90.business.rc.persistence;

import de.dasmo90.business.rc.api.User;
import de.dasmo90.business.rc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractUserAwareService {

	@Autowired
	private UserService userService;

	protected User obtainPersistedUser(User user) {
		if (user.getId() == 0) {
			return this.userService.fetchUserByName(user.getName());
		} else {
			return user;
		}
	}

}

