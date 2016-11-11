package de.dasmo90.business.rc.persistence;

import de.dasmo90.business.rc.TestMockFactory;
import de.dasmo90.business.rc.TestObjectProducer;
import de.dasmo90.business.rc.TestSpringPersistenceConfig;
import de.dasmo90.business.rc.api.Auditable;
import de.dasmo90.business.rc.api.AuditableRentCalculation;
import de.dasmo90.business.rc.permissions.Permission;
import de.dasmo90.business.rc.permissions.RentCalculationPermission;
import de.dasmo90.business.rc.permissions.Role;
import de.dasmo90.business.rc.service.PermissionService;
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
import java.util.List;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		TestSpringPersistenceConfig.class,
		TestObjectProducer.class,
		TestMockFactory.class,
		PermissionServiceImpl.class,
})
public class PermissionServiceTest {

	public static final String USER_NAME = "Peter Pommes";

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private TestObjectProducer testObjectProducer;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private UserService userServiceMock;

	private UserEntity testUser;

	private RentCalculationEntity rentCalculation;

	@Before
	public void setUp() throws Exception {

		Mockito.reset(userServiceMock);

		this.testUser = new UserEntity();
		this.testUser.merge(testObjectProducer.produceUser(USER_NAME));

		this.entityManager.persist(testUser);

		PermissionEntity permissionEntity = new PermissionEntity();
		permissionEntity.setRolePermission(testUser, Role.ADMIN);

		this.entityManager.persist(permissionEntity);

		this.rentCalculation = new RentCalculationEntity();
		this.rentCalculation.setCreator(this.testUser);
		this.rentCalculation.setModifier(this.testUser);

		this.entityManager.persist(this.rentCalculation);

		this.entityManager.flush();
	}

	@Test
	public void testFetchRolesWithPersistedUser() throws Exception {
		List<Role> roles = this.permissionService.fetchRolesFor(this.testUser);

		Assert.assertEquals(1, roles.size());
		Assert.assertEquals(Role.ADMIN, roles.get(0));
	}

	@Test
	public void testFetchRolesWithNonePersistedUser() throws Exception {

		Mockito.when(this.userServiceMock.fetchUserByName(USER_NAME)).thenReturn(this.testUser);

		List<Role> roles = this.permissionService.fetchRolesFor(this.testObjectProducer.produceUser(USER_NAME));

		Assert.assertEquals(1, roles.size());
		Assert.assertEquals(Role.ADMIN, roles.get(0));
	}

	@Test(expected = javax.persistence.NoResultException.class)
	public void testFetchPermissionNone() {
		this.permissionService.fetchPermission(testUser, this.rentCalculation);
	}

	@Test
	public void testFetchPermission() {
		PermissionEntity permissionEntity = new PermissionEntity();
		permissionEntity.setRentCalculationPermission(this.testUser, this.rentCalculation, true, false);

		this.entityManager.persist(permissionEntity);
		this.entityManager.flush();

		RentCalculationPermission permission = this.permissionService.fetchPermission(testUser, this.rentCalculation);

		Assert.assertEquals(true, permission.getCanRead());
		Assert.assertEquals(false, permission.getCanUpdate());
	}
}
