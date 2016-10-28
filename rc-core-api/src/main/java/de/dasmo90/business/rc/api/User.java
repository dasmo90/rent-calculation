package de.dasmo90.business.rc.api;

import de.dasmo90.business.rc.api.Identifyable;

public interface User extends Identifyable {

	String getEncryptedPassword();
}
