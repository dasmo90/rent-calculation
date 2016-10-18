package de.dasmo90.business.rc.model;

import de.dasmo90.business.rc.api.Auditable;
import de.dasmo90.business.rc.api.Identifyable;

import java.util.List;

public interface RentCalculation extends Identifyable, Auditable {

	List<RentCalculationPosition> getRentCalculationPositions();

}
