package br.unitins.clinica.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Venda extends DefaultEntity implements Serializable {

	private static final long serialVersionUID = -6846890038203867414L;

	private LocalDate data;
	private Double totalVenda;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ItemVenda> listaItemVenda;

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Double getTotalVenda() {
		return totalVenda;
	}

	public void setTotalVenda(Double totalVenda) {
		this.totalVenda = totalVenda;
	}

	public List<ItemVenda> getListaItemVenda() {
		return listaItemVenda;
	}

	public void setListaItemVenda(List<ItemVenda> listaItemVenda) {
		this.listaItemVenda = listaItemVenda;
	}

}
