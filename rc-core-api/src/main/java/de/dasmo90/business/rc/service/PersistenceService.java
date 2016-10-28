package de.dasmo90.business.rc.service;

import de.dasmo90.business.rc.api.Auditable;
import de.dasmo90.business.rc.model.RentCalculation;
import de.dasmo90.business.rc.api.User;

public interface PersistenceService {

	<T extends RentCalculation & Auditable> T fetchAuditableRentCalculation(long id);

	boolean save(User user, RentCalculation rentCalculation);

	RentCalculation create(User creator);
}
