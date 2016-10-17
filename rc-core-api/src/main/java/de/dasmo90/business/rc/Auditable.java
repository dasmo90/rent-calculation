package de.dasmo90.business.rc;

import java.util.Date;

public interface Auditable {

	User getChanger();

	User getModifier();

	Date getLastChanged();

	Date getLastModified();
}
