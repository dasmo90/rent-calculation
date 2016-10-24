package de.dasmo90.business.rc.persistence;

import de.dasmo90.business.rc.model.RentCalculation;
import de.dasmo90.business.rc.model.User;
import de.dasmo90.business.rc.service.PersistenceService;
import org.springframework.stereotype.Service;

@Service
public class PersistenceServiceImpl implements PersistenceService {

	@Override
	public boolean save(User user, RentCalculation rentCalculation) {
		return false;
	}

	@Override
	public RentCalculation create(User user) {
		return null;
	}

}
