package de.dasmo90.business.rc.entities;

import de.dasmo90.business.rc.api.Auditable;
import de.dasmo90.business.rc.model.RentCalculation;
import de.dasmo90.business.rc.model.RentCalculationPosition;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "RENT_CALCULATION")
public class RentCalculationEntity extends BaseAuditableEntity implements RentCalculation, Auditable {

	private String name;

	@OneToMany(targetEntity = RentCalculationPositionEntity.class)
	private List<RentCalculationPosition> rentCalculationPositions;

	protected RentCalculationEntity() {
	}

	protected void merge(RentCalculation rentCalculation) {
		name = rentCalculation.getName();
		rentCalculationPositions = new LinkedList<>(rentCalculation.getRentCalculationPositions());
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<RentCalculationPosition> getRentCalculationPositions() {
		return rentCalculationPositions;
	}

	@Override
	public String toString() {
		return "RentCalculationEntity{" +
				"name='" + name + '\'' +
				"creator='" + getCreator().getName() + '\'' +
				"modifier='" + getModifier().getName() + '\'' +
				", rentCalculationPositions=" + rentCalculationPositions +
				'}';
	}
}
