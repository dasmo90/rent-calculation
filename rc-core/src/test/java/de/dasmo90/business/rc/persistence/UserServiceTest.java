package de.dasmo90.business.rc.persistence;

import de.dasmo90.business.rc.TestObjectProducer;
import de.dasmo90.business.rc.TestSpringPersistenceConfig;
import de.dasmo90.business.rc.api.User;
import de.dasmo90.business.rc.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		TestSpringPersistenceConfig.class,
		TestObjectProducer.class,
		UserServiceImpl.class,
})
public class UserServiceTest {

	private static final String USER_NAME = "Peter Pommes";

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private TestObjectProducer testObjectProducer;

	@Autowired
	private UserService userService;

	@Test
	public void testOneUser() throws Exception {

		UserEntity testUser = new UserEntity();
		testUser.merge(testObjectProducer.produceUser(USER_NAME));

		this.entityManager.merge(testUser);
		this.entityManager.flush();

		User user = this.userService.fetchUserByName(USER_NAME);
		Assert.assertNotEquals(0, user.getId());
		Assert.assertEquals(USER_NAME, user.getName());
		Assert.assertEquals(TestObjectProducer.USER_PASSWORD, user.getEncryptedPassword());
	}

	@Test(expected = NoResultException.class)
	public void testNoneUser() throws Exception {
		this.userService.fetchUserByName(USER_NAME);
	}
}
