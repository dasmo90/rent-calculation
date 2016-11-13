package de.dasmo90.business.rc.persistence;

import de.dasmo90.business.rc.api.AuditableRentCalculation;
import de.dasmo90.business.rc.api.User;
import de.dasmo90.business.rc.permissions.RentCalculationPermission;
import de.dasmo90.business.rc.permissions.Role;
import de.dasmo90.business.rc.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl extends AbstractUserAwareService implements PermissionService {

	@PersistenceContext
	private EntityManager entityManager;

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

	@Override
	public List<Role> fetchRolesFor(User user) {
		final User persistedUser = obtainPersistedUser(user);
		List<PermissionEntity> permissions = this.entityManager.createNamedQuery(
				PermissionEntity.Query.FETCH_ROLE_PERMISSIONS_BY_USER_ID, PermissionEntity.class)
				.setParameter(PermissionEntity.Param.USER_ID, persistedUser.getId()).getResultList();
		return permissions.stream().map(PermissionEntity::getRole).collect(Collectors.toList());
	}


}
