package de.dasmo90.business.rc.persistence;

import de.dasmo90.business.rc.api.User;
import de.dasmo90.business.rc.model.RentCalculation;
import de.dasmo90.business.rc.permissions.RentCalculationPermission;
import de.dasmo90.business.rc.permissions.Role;
import de.dasmo90.business.rc.permissions.RolePermission;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PERMISSION")
public class PermissionEntity implements RentCalculationPermission, RolePermission {

	@Id
	@GeneratedValue
	public long id;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = RentCalculationEntity.class)
	@JoinColumn(name = "RENT_CALCULATION_ID")
	public RentCalculation rentCalculation;

	@Override
	public RentCalculation getRentCalculation() {
		return null;
	}

	@Override
	public boolean getCanRead() {
		return false;
	}

	@Override
	public boolean getCanUpdate() {
		return false;
	}

	@Override
	public Role getRole() {
		return null;
	}

	@Override
	public User getUser() {
		return null;
	}
}
