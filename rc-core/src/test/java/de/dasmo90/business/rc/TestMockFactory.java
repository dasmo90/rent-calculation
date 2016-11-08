package de.dasmo90.business.rc;

import de.dasmo90.business.rc.service.UserService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestMockFactory {

	@Bean
	public UserService getMockedUserService() {
		return Mockito.mock(UserService.class);
	}
}
