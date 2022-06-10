package br.unitins.clinica.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.unitins.clinica.model.Sexo;

@Converter(autoApply = true)
public class SexoConverter implements AttributeConverter<Sexo, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Sexo sexo) {
		return sexo == null ? null : sexo.getId();
	}

	@Override
	public Sexo convertToEntityAttribute(Integer dbData) {
		return Sexo.valueOf(dbData);
	}

}
