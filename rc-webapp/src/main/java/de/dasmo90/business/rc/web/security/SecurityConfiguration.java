package de.dasmo90.business.rc.web.security;

import de.dasmo90.business.rc.api.User;
import de.dasmo90.business.rc.permissions.Role;
import de.dasmo90.business.rc.service.PermissionService;
import de.dasmo90.business.rc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(SecurityConfiguration.class);

	@Autowired
	private UserService userService;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(name -> {
			User user = SecurityConfiguration.this.userService.fetchUserByName(name);
			List<Role> roles = SecurityConfiguration.this.permissionService.fetchRolesFor(user);
			LOG.debug("Loaded user {} with {} roles: {}", user.getName(), roles.size(), roles);
			return new ValidUserDetails(user, roles);
		}).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/save").access(RoleUtils.hasRole(Role.WRITE))
				.antMatchers("/create").access(RoleUtils.hasRole(Role.CREATE))
				.and().formLogin();
	}
}
