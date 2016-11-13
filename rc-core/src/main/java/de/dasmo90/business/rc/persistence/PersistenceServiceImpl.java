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
public class PersistenceServiceImpl extends AbstractUserAwareService implements PersistenceService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public AuditableRentCalculation fetchAuditableRentCalculation(long id) {
		return this.entityManager.find(RentCalculationEntity.class, id);
	}

	@Override
	@Transactional
	public void save(User user, RentCalculation rentCalculation) {
		User persistedUser = this.obtainPersistedUser(user);
		RentCalculationEntity rentCalculationEntity = this.entityManager.find(RentCalculationEntity.class,
				rentCalculation.getId());
		rentCalculationEntity.setModifier(persistedUser);
		rentCalculationEntity.merge(rentCalculation);
	}

	@Override
	@Transactional
	public RentCalculation create(User user) {
		User persistedUser = this.obtainPersistedUser(user);
		RentCalculationEntity rentCalculationEntity = new RentCalculationEntity();
		rentCalculationEntity.setCreator(persistedUser);
		rentCalculationEntity.setModifier(persistedUser);
		this.entityManager.persist(rentCalculationEntity);
//		this.entityManager.detach(rentCalculationEntity);
//		this.entityManager.flush();
		return rentCalculationEntity;
	}

}
