package de.dasmo90.business.rc.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDateTime locDate) {
		if (locDate == null) {
			return null;
		}
		return Date.from(locDate.atZone(ZoneId.systemDefault()).toInstant());
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Date sqlDate) {
		if (sqlDate == null) {
			return null;
		}
		return LocalDateTime.ofInstant(sqlDate.toInstant(), ZoneId.systemDefault());
	}
}
