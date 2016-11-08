package de.dasmo90.business.rc.persistence;

import de.dasmo90.business.rc.api.AuditableRentCalculation;
import de.dasmo90.business.rc.api.User;
import de.dasmo90.business.rc.permissions.Role;
import de.dasmo90.business.rc.service.PermissionService;
import de.dasmo90.business.rc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private UserService userService;

	@Override
	public boolean canRead(User user, AuditableRentCalculation rentCalculation) {

		if (user.getId() == rentCalculation.getCreator().getId()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canUpdate(User user, AuditableRentCalculation rentCalculation) {
		return false;
	}

	@Override
	public List<Role> fetchRolesFor(User user) {

		final User persistedUser;
		if (user.getId() == 0) {
			persistedUser = this.userService.fetchUserByName(user.getName());
		} else {
			persistedUser = user;
		}

		List<PermissionEntity> permissions = this.entityManager.createNamedQuery(
				PermissionEntity.Query.FETCH_ROLE_PERMISSIONS_BY_USER_ID, PermissionEntity.class)
				.setParameter(PermissionEntity.Param.USER_ID, persistedUser.getId()).getResultList();

		return permissions.stream().map(PermissionEntity::getRole).collect(Collectors.toList());
	}


}
