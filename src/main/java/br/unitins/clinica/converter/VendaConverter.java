package br.unitins.clinica.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import br.unitins.clinica.application.RepositoryException;
import br.unitins.clinica.model.Venda;
import br.unitins.clinica.repository.VendaRepository;

@Named
@FacesConverter(forClass = Venda.class)
public class VendaConverter implements Converter<Venda>{

	@Override
	public Venda getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isBlank())
			return null;
		VendaRepository repo = new VendaRepository();
		try {
			return repo.findById(Integer.parseInt(value));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Venda value) {
		if (value == null || value.getId() == null)
			return null;
		return value.getId().toString();
	}

}
