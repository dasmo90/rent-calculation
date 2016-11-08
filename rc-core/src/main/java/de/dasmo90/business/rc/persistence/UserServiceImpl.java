package de.dasmo90.business.rc.persistence;

import de.dasmo90.business.rc.api.User;
import de.dasmo90.business.rc.service.UserService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class UserServiceImpl implements UserService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User fetchUserByName(String name) {
		return entityManager
				.createNamedQuery(UserEntity.Query.GET_BY_NAME, UserEntity.class)
				.setParameter(UserEntity.Param.NAME, name)
				.getSingleResult();
	}
}
