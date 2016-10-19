package de.dasmo90.business.rc.dto;

import de.dasmo90.business.rc.model.RentCalculation;
import de.dasmo90.business.rc.model.RentCalculationPosition;
import de.dasmo90.business.rc.model.User;

import java.time.LocalDateTime;
import java.util.List;

public class RentCalculationDto implements RentCalculation {

	private long id;

	private String name;

	private List<RentCalculationPosition> rentCalculationPositions;

	@Override
	public List<RentCalculationPosition> getRentCalculationPositions() {
		return rentCalculationPositions;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public User getChanger() {
		return null;
	}

	@Override
	public User getModifier() {
		return null;
	}

	@Override
	public LocalDateTime getLastChanged() {
		return null;
	}

	@Override
	public LocalDateTime getLastModified() {
		return null;
	}
}
