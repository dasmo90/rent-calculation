package de.dasmo90.business.rc.entities;

import de.dasmo90.business.rc.model.Payment;
import de.dasmo90.business.rc.model.RentCalculationPosition;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "RENT_CALCULATION_POSITION")
public class RentCalculationPositionEntity implements RentCalculationPosition {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	protected RentCalculationPositionEntity() {

	}

	protected RentCalculationPositionEntity(RentCalculationPosition rentCalculationPosition) {
		id = rentCalculationPosition.getId();
		name = rentCalculationPosition.getName();
	}

	@Override
	public List<Payment> getPayments() {
		return null;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
}
