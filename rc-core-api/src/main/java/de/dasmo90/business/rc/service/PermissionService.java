package de.dasmo90.business.rc.service;

import de.dasmo90.business.rc.api.Auditable;
import de.dasmo90.business.rc.api.AuditableRentCalculation;
import de.dasmo90.business.rc.api.User;
import de.dasmo90.business.rc.permissions.Role;

import java.util.List;

public interface PermissionService {

	boolean canRead(User user, AuditableRentCalculation rentCalculation);

	boolean canUpdate(User user, AuditableRentCalculation rentCalculation);

	List<Role> fetchRolesFor(User user);
}
