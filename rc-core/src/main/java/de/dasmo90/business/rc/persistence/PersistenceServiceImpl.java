package de.dasmo90.business.rc.persistence;

import de.dasmo90.business.rc.api.AuditableRentCalculation;
import de.dasmo90.business.rc.api.User;
import de.dasmo90.business.rc.model.RentCalculation;
import de.dasmo90.business.rc.service.PersistenceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class PersistenceServiceImpl implements PersistenceService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public AuditableRentCalculation fetchAuditableRentCalculation(long id) {
		return this.entityManager.find(RentCalculationEntity.class, id);
	}

	@Override
	@Transactional
	public boolean save(User user, RentCalculation rentCalculation) {
		RentCalculationEntity rentCalculationEntity = this.entityManager.find(RentCalculationEntity.class,
				rentCalculation.getId());
		rentCalculationEntity.merge(rentCalculation);
		rentCalculationEntity.setModifier(user);
		return false;
	}

	@Override
	public RentCalculation create(User user) {
		return null;
	}

}
