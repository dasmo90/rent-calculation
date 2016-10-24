package de.dasmo90.business.rc.api;

import de.dasmo90.business.rc.model.User;

import java.time.LocalDateTime;

public interface Auditable {

	User getCreator();

	User getModifier();

	LocalDateTime getCreated();

	LocalDateTime getLastModified();
}
