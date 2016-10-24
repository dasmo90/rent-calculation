package de.dasmo90.business.rc.model;

import de.dasmo90.business.rc.api.Identifyable;

import java.util.List;

public interface RentCalculation extends Identifyable {

	List<RentCalculationPosition> getRentCalculationPositions();

}
