package de.dasmo90.business.rc.model;

import java.util.List;

public interface Recurrence {

	List<RecurrenceUnit> getUnit();

	int getValue();
}
