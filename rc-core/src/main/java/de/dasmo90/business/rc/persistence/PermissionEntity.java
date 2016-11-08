package de.dasmo90.business.rc.persistence;

import de.dasmo90.business.rc.api.User;
import de.dasmo90.business.rc.model.RentCalculation;
import de.dasmo90.business.rc.permissions.Permission;
import de.dasmo90.business.rc.permissions.RentCalculationPermission;
import de.dasmo90.business.rc.permissions.Role;
import de.dasmo90.business.rc.permissions.RolePermission;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "PERMISSION")
@NamedQueries({
		@NamedQuery(
				name = PermissionEntity.Query.FETCH_ROLE_PERMISSIONS_BY_USER_ID,
				query = "SELECT permission " +
						"FROM PermissionEntity permission " +
						"WHERE permission.role IS NOT NULL " +
						"AND permission.user.id = :" + PermissionEntity.Param.USER_ID)
})
public class PermissionEntity implements RentCalculationPermission, RolePermission {

	public interface Query {
		String FETCH_ROLE_PERMISSIONS_BY_USER_ID = "fetchRolePermissionsByUserId";
	}

	public interface Param {
		String USER_ID = "userId";
	}

	@Id
	@GeneratedValue
	private long id;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = RentCalculationEntity.class)
	@JoinColumn(name = "RENT_CALCULATION_ID")
	private RentCalculation rentCalculation;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
	@JoinColumn(name = "USER_ID")
	private User user;

	private boolean canRead;

	private boolean canUpdate;

	private Role role;

	protected void setRolePermission(User user, Role role) {
		this.user = user;
		this.role = role;
	}

	@Override
	public RentCalculation getRentCalculation() {
		return this.rentCalculation;
	}

	@Override
	public boolean getCanRead() {
		return this.canRead;
	}

	@Override
	public boolean getCanUpdate() {
		return this.canUpdate;
	}

	@Override
	public Role getRole() {
		return this.role;
	}

	@Override
	public User getUser() {
		return this.user;
	}
}
