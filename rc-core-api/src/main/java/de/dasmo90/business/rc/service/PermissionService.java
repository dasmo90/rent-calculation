package de.dasmo90.business.rc.service;

import de.dasmo90.business.rc.model.RentCalculation;
import de.dasmo90.business.rc.model.User;
import de.dasmo90.business.rc.permissions.Role;

import java.util.List;

public interface PermissionService {

	boolean canRead(User user, RentCalculation rentCalculation);

	boolean canUpdate(User user, RentCalculation rentCalculation);

	List<Role> fetchRolesFor(User user);
}
