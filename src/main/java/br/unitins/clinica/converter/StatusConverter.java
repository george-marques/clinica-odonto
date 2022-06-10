package br.unitins.clinica.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.unitins.clinica.model.Status;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Status status) {
		return status == null ? null : status.getId();
	}

	@Override
	public Status convertToEntityAttribute(Integer dbData) {
		return Status.valueOf(dbData);
	}

}
