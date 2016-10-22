package de.dasmo90.business.rc.entities;

import de.dasmo90.business.rc.model.RentCalculation;
import de.dasmo90.business.rc.service.PersistenceService;
import org.springframework.stereotype.Service;

@Service
public class PersistenceServiceImpl implements PersistenceService {

	@Override
	public boolean save(RentCalculation rentCalculation) {
		return false;
	}

	@Override
	public RentCalculation create() {
		return null;
	}

}
