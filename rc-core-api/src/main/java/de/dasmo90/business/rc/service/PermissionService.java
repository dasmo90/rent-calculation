package de.dasmo90.business.rc.service;

import de.dasmo90.business.rc.api.Auditable;
import de.dasmo90.business.rc.api.User;
import de.dasmo90.business.rc.permissions.Role;

import java.util.List;

public interface PermissionService {

	boolean canRead(User user, Auditable rentCalculation);

	boolean canUpdate(User user, Auditable rentCalculation);

	List<Role> fetchRolesFor(User user);
}
