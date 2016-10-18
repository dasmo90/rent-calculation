package de.dasmo90.business.rc.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate locDate) {
		if (locDate == null) {
			return null;
		}
		return Date.from(locDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	@Override
	public LocalDate convertToEntityAttribute(Date sqlDate) {
		if (sqlDate == null) {
			return null;
		}
		return LocalDateTime.ofInstant(sqlDate.toInstant(), ZoneId.systemDefault()).toLocalDate();
	}
}
