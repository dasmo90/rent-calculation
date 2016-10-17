package de.dasmo90.business.rc;

public interface User extends Identifyable, Auditable {

	String getPasswordSalt();
}
