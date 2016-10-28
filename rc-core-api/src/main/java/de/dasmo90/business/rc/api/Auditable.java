package de.dasmo90.business.rc.api;

import java.time.LocalDateTime;

public interface Auditable {

	User getCreator();

	User getModifier();

	LocalDateTime getCreated();

	LocalDateTime getLastModified();
}
