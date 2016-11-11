package de.dasmo90.business.rc.mocks;

import de.dasmo90.business.rc.api.AuditableRentCalculation;
import de.dasmo90.business.rc.api.User;
import de.dasmo90.business.rc.model.RentCalculation;
import de.dasmo90.business.rc.permissions.RentCalculationPermission;
import de.dasmo90.business.rc.permissions.Role;
import de.dasmo90.business.rc.service.PermissionService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Primary
@Service
public class PermissionServiceMock implements PermissionService {

	@Override
	public RentCalculationPermission fetchPermission(User user, AuditableRentCalculation rentCalculation) {
		return new RentCalculationPermission() {
			@Override
			public RentCalculation getRentCalculation() {
				return rentCalculation;
			}

			@Override
			public boolean getCanRead() {
				return true;
			}

			@Override
			public boolean getCanUpdate() {
				return true;
			}

			@Override
			public User getUser() {
				return user;
			}
		};
	}

	@Override
	public List<Role> fetchRolesFor(User user) {
		LinkedList<Role> roles = new LinkedList<>();
		roles.add(Role.CREATE);
		roles.add(Role.READ);
		roles.add(Role.WRITE);
		return roles;
	}
}
