package de.dasmo90.business.rc.service;

import de.dasmo90.business.rc.model.RentCalculation;
import de.dasmo90.business.rc.model.User;
import de.dasmo90.business.rc.permissions.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Override
	public boolean canRead(User user, RentCalculation rentCalculation) {
		return false;
	}

	@Override
	public boolean canUpdate(User user, RentCalculation rentCalculation) {
		return false;
	}

	@Override
	public List<Role> fetchRolesFor(User user) {
		return null;
	}


}
