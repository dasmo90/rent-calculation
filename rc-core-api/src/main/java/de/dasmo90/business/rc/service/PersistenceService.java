package de.dasmo90.business.rc.service;

import de.dasmo90.business.rc.model.RentCalculation;
import de.dasmo90.business.rc.model.User;

public interface PersistenceService {

	boolean save(RentCalculation rentCalculation);

	void createRentCalculation(User user);
}
