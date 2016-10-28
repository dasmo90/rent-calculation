package de.dasmo90.business.rc.persistence;

import de.dasmo90.business.rc.api.Auditable;
import de.dasmo90.business.rc.api.User;
import de.dasmo90.business.rc.permissions.Role;
import de.dasmo90.business.rc.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Override
	public boolean canRead(User user, Auditable auditable) {

		if (user.getId() == auditable.getCreator().getId()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canUpdate(User user, Auditable rentCalculation) {
		return false;
	}

	@Override
	public List<Role> fetchRolesFor(User user) {
		return null;
	}


}
