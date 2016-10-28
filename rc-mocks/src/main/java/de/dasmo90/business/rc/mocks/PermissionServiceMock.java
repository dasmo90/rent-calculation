package de.dasmo90.business.rc.mocks;

import de.dasmo90.business.rc.api.Auditable;
import de.dasmo90.business.rc.model.RentCalculation;
import de.dasmo90.business.rc.api.User;
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
	public boolean canRead(User user, Auditable rentCalculation) {
		return true;
	}

	@Override
	public boolean canUpdate(User user, Auditable rentCalculation) {
		return true;
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
