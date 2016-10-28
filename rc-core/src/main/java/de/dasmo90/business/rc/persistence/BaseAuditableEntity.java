package de.dasmo90.business.rc.persistence;

import de.dasmo90.business.rc.api.Auditable;
import de.dasmo90.business.rc.api.User;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseAuditableEntity extends BaseEntity implements Auditable {

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
	@JoinColumn(name = "CHANGER_ID")
	private User creator;

	private LocalDateTime created;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
	@JoinColumn(name = "MODIFIER_ID")
	private User modifier;

	private LocalDateTime lastModified;

	@Override
	public User getCreator() {
		return creator;
	}

	protected final void setCreator(@NotNull User creator) {
		if (creator == null) {
			throw new IllegalArgumentException("Creator cannot be null.");
		}
		this.creator = createUserEntity(creator);
	}

	@Override
	public LocalDateTime getCreated() {
		return created;
	}

	@Override
	public User getModifier() {
		return modifier;
	}

	protected final void setModifier(@NotNull User modifier) {
		if (modifier == null) {
			throw new IllegalArgumentException("Modifier cannot be null.");
		}
		this.modifier = createUserEntity(modifier);
	}

	@Override
	public LocalDateTime getLastModified() {
		return lastModified;
	}

	@PreUpdate
	public void preUpdate() {
		this.lastModified = LocalDateTime.now();
		if (this.created == null) {
			throw new IllegalStateException("No creation date set.");
		}
		if (this.creator == null) {
			throw new IllegalStateException("No creator was set in rent calculation was set.");
		}
		if (this.modifier == null) {
			throw new IllegalStateException("No modifier was set in rent calculation was set.");
		}
	}

	@PrePersist
	public void prePersist() {
		this.created = LocalDateTime.now();
		this.lastModified = LocalDateTime.from(this.created);
		if (this.creator == null) {
			throw new IllegalStateException("No creator was set in rent calculation was set.");
		}
		this.modifier = createUserEntity(this.creator);
	}

	private UserEntity createUserEntity(User user) {
		if (user == null) {
			return null;
		}
		UserEntity userEntity = new UserEntity();
		userEntity.setId(user.getId());
		userEntity.merge(user);
		return userEntity;
	}
}
