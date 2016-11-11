package de.dasmo90.business.rc.service;

import de.dasmo90.business.rc.api.Auditable;
import de.dasmo90.business.rc.api.AuditableRentCalculation;
import de.dasmo90.business.rc.api.User;
import de.dasmo90.business.rc.permissions.Permission;
import de.dasmo90.business.rc.permissions.RentCalculationPermission;
import de.dasmo90.business.rc.permissions.Role;

import java.util.List;

public interface PermissionService {

	RentCalculationPermission fetchPermission(User user, AuditableRentCalculation rentCalculation);

	List<Role> fetchRolesFor(User user);
}
