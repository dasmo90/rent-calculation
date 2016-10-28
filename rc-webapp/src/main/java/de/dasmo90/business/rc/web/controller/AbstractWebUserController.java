package de.dasmo90.business.rc.web.controller;

import de.dasmo90.business.rc.api.User;
import de.dasmo90.business.rc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

public abstract class AbstractWebUserController {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UserService userService;

	protected User retrieveUser() {
		Principal userPrincipal = this.request.getUserPrincipal();
		if(userPrincipal == null) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
		}
		User user = this.userService.fetchUserByName(userPrincipal.getName());
		if(user == null) {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
		}
		return user;
	}
}
