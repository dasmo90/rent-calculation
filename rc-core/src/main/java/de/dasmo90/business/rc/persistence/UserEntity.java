package de.dasmo90.business.rc.persistence;

import de.dasmo90.business.rc.api.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
@NamedQueries(
		@NamedQuery(
				name = UserEntity.Query.GET_BY_NAME,
				query = "SELECT user FROM UserEntity user WHERE user.name = :" + UserEntity.Param.NAME)
)
public class UserEntity implements User {

	public interface Query {
		String GET_BY_NAME = "getByName";
	}

	public interface Param {
		String NAME = "name";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	private String passwordSalt;

	protected UserEntity() {
	}

	protected void merge(User user) {
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

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}
}
