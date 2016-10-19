package de.dasmo90.business.rc.service;

import de.dasmo90.business.rc.model.RentCalculation;

public interface PermissionService {

	boolean canRead(RentCalculation rentCalculation);

	boolean canUpdate(RentCalculation rentCalculation);
}
