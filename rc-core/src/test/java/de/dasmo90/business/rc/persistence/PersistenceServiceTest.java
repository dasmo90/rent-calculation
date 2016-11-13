package de.dasmo90.business.rc.persistence;

import de.dasmo90.business.rc.TestMockFactory;
import de.dasmo90.business.rc.TestObjectProducer;
import de.dasmo90.business.rc.TestSpringPersistenceConfig;
import de.dasmo90.business.rc.api.AuditableRentCalculation;
import de.dasmo90.business.rc.model.RentCalculation;
import de.dasmo90.business.rc.service.PersistenceService;
import de.dasmo90.business.rc.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		TestSpringPersistenceConfig.class,
		TestObjectProducer.class,
		TestMockFactory.class,
		PersistenceServiceImpl.class,
})
public class PersistenceServiceTest {

	public static final String USER_NAME = "Peter Pommes";

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private TestObjectProducer testObjectProducer;

	@Autowired
	private PersistenceService persistenceService;

	@Autowired
	private UserService userServiceMock;

	private UserEntity testUser;

	private RentCalculation rentCalculation;

	@Before
	public void setUp() throws Exception {

		Mockito.reset(userServiceMock);

		this.testUser = new UserEntity();
		this.testUser.merge(testObjectProducer.produceUser(USER_NAME));

		this.entityManager.persist(this.testUser);
		this.entityManager.detach(this.testUser);
		this.entityManager.flush();

		this.rentCalculation = this.persistenceService.create(this.testUser);
	}

	@Test
	public void testFetchRentCalculation() throws Exception {
		AuditableRentCalculation auditableRentCalculation =
				this.persistenceService.fetchAuditableRentCalculation(this.rentCalculation.getId());
		Assert.assertEquals(this.rentCalculation.getName(), auditableRentCalculation.getName());
		Assert.assertEquals(this.testUser.getId(), auditableRentCalculation.getModifier().getId());
		Assert.assertEquals(this.testUser.getId(), auditableRentCalculation.getCreator().getId());
	}
}
