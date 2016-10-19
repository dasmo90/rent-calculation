package de.dasmo90.business.rc;

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
	public void createRentCalculation() {

	}
}
