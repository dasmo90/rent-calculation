package de.dasmo90.business.rc.persistence;

import de.dasmo90.business.rc.api.AuditableRentCalculation;
import de.dasmo90.business.rc.api.User;
import de.dasmo90.business.rc.model.RentCalculation;
import de.dasmo90.business.rc.service.PersistenceService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class PersistenceServiceImpl implements PersistenceService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public AuditableRentCalculation fetchAuditableRentCalculation(long id) {
		return entityManager.find(RentCalculationEntity.class, id);
	}

	@Override
	public boolean save(User user, RentCalculation rentCalculation) {
		return false;
	}

	@Override
	public RentCalculation create(User user) {
		return null;
	}

}
