package de.dasmo90.business.rc.service;

import de.dasmo90.business.rc.model.RentCalculation;

public interface PersistenceService {

	boolean save(RentCalculation rentCalculation);

	RentCalculation create();
}
