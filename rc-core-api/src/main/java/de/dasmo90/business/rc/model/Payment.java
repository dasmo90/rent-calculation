package de.dasmo90.business.rc.model;

import de.dasmo90.business.rc.api.Identifyable;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface Payment extends Identifyable {

	BigDecimal getValues();

	Recurrence getPeriod();

	LocalDate getStart();

	int getOccurrence();
}
