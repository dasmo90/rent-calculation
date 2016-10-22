package de.dasmo90.business.rc.entities;

import de.dasmo90.business.rc.model.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RENT_CALCULATION_POSITION")
public class UserEntity implements User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	private String passwordSalt;

	protected UserEntity() {

	}

	public UserEntity(User user) {
		id = user.getId();
		name = user.getName();
		passwordSalt = user.getEncryptedPassword();
	}

	@Override
	public String getEncryptedPassword() {
		return passwordSalt;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
}
