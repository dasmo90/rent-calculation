package de.dasmo90.business.rc.persistence;

import de.dasmo90.business.rc.entities.RentCalculationEntity;
import de.dasmo90.business.rc.model.RentCalculation;
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
import java.util.Arrays;
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
	public void testName() throws Exception {

		RentCalculationEntity rentCalculationEntity = new RentCalculationEntity(testEntityProducer.produce(0, "Test"));

		entityManager.persist(rentCalculationEntity);

		entityManager.flush();

		List<RentCalculation> resultList =
				entityManager.createQuery("SELECT e FROM RentCalculationEntity e", RentCalculation.class).getResultList();

		LOG.info(Arrays.toString(resultList.stream().map(RentCalculation::getName).toArray()));
	}
}
