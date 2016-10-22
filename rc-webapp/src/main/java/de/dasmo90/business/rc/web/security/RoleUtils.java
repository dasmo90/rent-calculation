package de.dasmo90.business.rc.web.security;

import de.dasmo90.business.rc.permissions.Role;

public class RoleUtils {

	public static final String ROLE_PREFIX = "ROLE_";
	public static final String SPACE = " ";

	public static String genRoleName(Role role) {
		return ROLE_PREFIX + role.name();
	}

	public static String hasRole(Role role) {
		return String.format("hasRole('%s')", genRoleName(role));
	}

	private static String rolesConcat(String divider, Role... roles) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0;i < roles.length;i++) {
			if(i > 0) {
				sb.append(SPACE);
				sb.append(divider);
				sb.append(SPACE);
			}
			sb.append(hasRole(roles[i]));
		}
		return sb.toString();
	}

	public static String hasAllRoles(Role... roles) {
		return rolesConcat("and", roles);
	}

	public static String hasAnyRole(Role... roles) {
		return rolesConcat("or", roles);
	}

}
