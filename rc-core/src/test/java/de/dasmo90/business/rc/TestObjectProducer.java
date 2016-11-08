package de.dasmo90.business.rc;

import de.dasmo90.business.rc.model.RentCalculation;
import de.dasmo90.business.rc.model.RentCalculationPosition;
import de.dasmo90.business.rc.api.User;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class TestObjectProducer {

	public static final String USER_PASSWORD = "enc";

	public RentCalculation produceRentCalculation(long id, String name) {
		return new RentCalculation() {

			@Override
			public List<RentCalculationPosition> getRentCalculationPositions() {
				return Collections.emptyList();
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

	public User produceUser(long id, String name) {
		return new User() {
			@Override
			public String getEncryptedPassword() {
				return USER_PASSWORD;
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
