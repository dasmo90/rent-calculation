package de.dasmo90.business.rc.api;

import de.dasmo90.business.rc.model.User;

import java.time.LocalDateTime;

public interface Auditable {

	User getChanger();

	User getModifier();

	LocalDateTime getLastChanged();

	LocalDateTime getLastModified();
}
