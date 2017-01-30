package br.com.victor.famintos.managed;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.victor.famintos.ejb.impl.VotoSessionBean;
import br.com.victor.famintos.exception.StepsException;
import br.com.victor.famintos.model.impl.Profissional;
import br.com.victor.famintos.model.impl.Restaurante;

/**
 * @author Victor H. Colombo
 * @since 28/01/2017
 */
@ManagedBean
@SessionScoped
public class RestauranteMB {
	private List<Restaurante> restaurantes;
	private Restaurante restaurante;
	private Profissional profissional;

	@EJB
	private VotoSessionBean service;

	@PostConstruct
	public void initPage() throws StepsException {
		restaurantes = new ArrayList<Restaurante>();
		restaurantes.add(new Restaurante(1l, "Juventus"));
		restaurantes.add(new Restaurante(2l, "Parthenon"));
		restaurantes.add(new Restaurante(3l, "Agartha"));
		restaurantes.add(new Restaurante(4l, "Aztlan"));
	}

	public RestauranteMB() {
		restaurantes = new ArrayList<Restaurante>();
	}

	public List<Restaurante> getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public String buttonActionVoltar() {
		return "stepOne?faces-redirect=true";
	}

}
