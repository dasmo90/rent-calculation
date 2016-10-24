package de.dasmo90.business.rc.persistence;

import de.dasmo90.business.rc.model.RentCalculation;
import de.dasmo90.business.rc.model.User;
import de.dasmo90.business.rc.test.TestEntityProducer;
import de.dasmo90.business.rc.test.TestSpringPersistenceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = {
		TestEntityProducer.class,
		TestSpringPersistenceConfig.class
})
public class RcPersistenceTest {

	private Logger LOG = LoggerFactory.getLogger(RcPersistenceTest.class);

	@Autowired
	private TestEntityProducer testEntityProducer;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	public void testPersistence() throws Exception {

		User testuser = testEntityProducer.produceUser(27, "Testuser");
		UserEntity userEntity = new UserEntity();
		userEntity.merge(testuser);
		entityManager.persist(userEntity);
		entityManager.flush();

		RentCalculationEntity rentCalculationEntity = new RentCalculationEntity();
		rentCalculationEntity.merge(testEntityProducer.produceRentCalculation(0, "Test"));
		rentCalculationEntity.setCreator(userEntity);

		entityManager.persist(rentCalculationEntity);
		entityManager.flush();

		rentCalculationEntity.merge(testEntityProducer.produceRentCalculation(55, "Blubb"));

		entityManager.persist(rentCalculationEntity);
		entityManager.flush();

		List<RentCalculation> resultList =
				entityManager.createQuery("SELECT e FROM RentCalculationEntity e", RentCalculation.class).getResultList();

		LOG.info(resultList.toString());
	}
}
