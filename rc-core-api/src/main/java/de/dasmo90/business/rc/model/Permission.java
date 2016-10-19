package de.dasmo90.business.rc.model;

public interface Permission {

	RentCalculation getRentCalculation();

	User getUser();

	boolean getCanRead();

	boolean getCanUpdate();
}
