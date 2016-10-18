package de.dasmo90.business.rc.entities;

import de.dasmo90.business.rc.model.RentCalculation;
import de.dasmo90.business.rc.model.RentCalculationPosition;
import de.dasmo90.business.rc.model.User;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "RENT_CALCULATION")
public class RentCalculationEntity implements RentCalculation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	@OneToMany(targetEntity = RentCalculationPositionEntity.class)
	private List<RentCalculationPosition> rentCalculationPositions;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
	@JoinColumn(name = "CHANGER_ID")
	private User changer;

	private LocalDateTime lastChanged;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
	@JoinColumn(name = "MODIFIER_ID")
	private User modifier;

	private LocalDateTime lastModified;

	protected RentCalculationEntity() {
	}

	public RentCalculationEntity(RentCalculation rentCalculation) {
		id = rentCalculation.getId();
		name = rentCalculation.getName();
		this.changer = createUserEntity(rentCalculation.getChanger());
		lastChanged = rentCalculation.getLastChanged();
		modifier = createUserEntity(rentCalculation.getModifier());
		lastModified = rentCalculation.getLastModified();
	}

	private UserEntity createUserEntity(User user) {
		return user == null ? null : new UserEntity(user);
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
	public List<RentCalculationPosition> getRentCalculationPositions() {
		return rentCalculationPositions;
	}

	@Override
	public User getChanger() {
		return changer;
	}

	@Override
	public LocalDateTime getLastChanged() {
		return lastChanged;
	}

	@Override
	public User getModifier() {
		return modifier;
	}

	@Override
	public LocalDateTime getLastModified() {
		return lastModified;
	}
}
