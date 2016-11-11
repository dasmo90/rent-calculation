package de.dasmo90.business.rc.persistence;

import de.dasmo90.business.rc.api.AuditableRentCalculation;
import de.dasmo90.business.rc.api.User;
import de.dasmo90.business.rc.model.RentCalculation;
import de.dasmo90.business.rc.permissions.Permission;
import de.dasmo90.business.rc.permissions.RentCalculationPermission;
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
	public RentCalculationPermission fetchPermission(User user, AuditableRentCalculation rentCalculation) {
		final User persistedUser = obtainPersistedUser(user);
		PermissionEntity permissionEntity = this.entityManager.createNamedQuery(
				PermissionEntity.Query.FETCH_RC_PERMISSION_BY_USER_ID_AND_RC_ID, PermissionEntity.class)
				.setParameter(PermissionEntity.Param.USER_ID, persistedUser.getId())
				.setParameter(PermissionEntity.Param.RC_ID, rentCalculation.getId())
				.getSingleResult();
		this.entityManager.detach(permissionEntity);
		return permissionEntity;
	}

	private User obtainPersistedUser(User user) {
		if (user.getId() == 0) {
			return this.userService.fetchUserByName(user.getName());
		} else {
			return user;
		}
	}

	@Override
	public List<Role> fetchRolesFor(User user) {
		final User persistedUser = obtainPersistedUser(user);
		List<PermissionEntity> permissions = this.entityManager.createNamedQuery(
				PermissionEntity.Query.FETCH_ROLE_PERMISSIONS_BY_USER_ID, PermissionEntity.class)
				.setParameter(PermissionEntity.Param.USER_ID, persistedUser.getId()).getResultList();
		return permissions.stream().map(PermissionEntity::getRole).collect(Collectors.toList());
	}


}
