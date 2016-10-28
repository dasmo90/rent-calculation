package de.dasmo90.business.rc.mocks;

import de.dasmo90.business.rc.api.User;
import de.dasmo90.business.rc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Primary
@Service
public class UserServiceMock implements UserService {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public User fetchUserByName(String name) {
		return new User() {

			@Override
			public long getId() {
				return 0;
			}

			@Override
			public String getName() {
				return "mo";
			}

			@Override
			public String getEncryptedPassword() {
				return passwordEncoder.encode("123");
			}
		};
	}
}
