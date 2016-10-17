package de.dasmo90.business.rc;

import java.util.List;

public interface RentCalculation extends Identifyable, Auditable {

	List<RentCalculationPosition> getRentCalculationPositions();
}
