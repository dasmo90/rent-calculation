package de.dasmo90.business.rc.web.security;

import de.dasmo90.business.rc.api.User;
import de.dasmo90.business.rc.permissions.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ValidUserDetails implements UserDetails {

	private final Collection<? extends GrantedAuthority> authorities;
	private String password;
	private String name;

	public ValidUserDetails(User user, List<Role> roles) {
		this.name = user.getName();
		this.password = user.getEncryptedPassword();
		this.authorities = roles.stream()
				.map(RoleUtils::genRoleName)
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
