package de.dasmo90.business.rc.permissions;

import de.dasmo90.business.rc.model.RentCalculation;

public interface RentCalculationPermission extends Permission {

	RentCalculation getRentCalculation();

	boolean getCanRead();

	boolean getCanUpdate();
}
