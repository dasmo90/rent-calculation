package de.dasmo90.business.rc.test;

import de.dasmo90.business.rc.model.RentCalculation;
import de.dasmo90.business.rc.model.RentCalculationPosition;
import de.dasmo90.business.rc.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Component
public class TestEntityProducer {

	public RentCalculation produce(long id, String name) {
		return new RentCalculation() {

			@Override
			public List<RentCalculationPosition> getRentCalculationPositions() {
				return Collections.emptyList();
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

			@Override
			public long getId() {
				return id;
			}

			@Override
			public String getName() {
				return name;
			}
		};
	}
}
