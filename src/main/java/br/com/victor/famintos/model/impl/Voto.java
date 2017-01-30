package br.com.victor.famintos.model.impl;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import br.com.victor.famintos.model.IModel;
import br.com.victor.famintos.util.DataUteis;


/**
 * @author Victor H. Colombo
 * @since 27/01/2017
 */
@Entity
@Table(name = "voto", uniqueConstraints={@UniqueConstraint(columnNames = {"data" , "id_profissional"})})
@NamedQueries({ @NamedQuery(name = "Voto.findByProfissionalInDate", query = "SELECT v FROM Voto v WHERE v.profissional.id = :profissionalId and v.data = :data") })
public class Voto implements IModel {
	private static final long serialVersionUID = -1857136987919779610L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@NotNull
	@Column(name = "data")
	@Temporal(value = TemporalType.DATE)
	private Date data;

	@ManyToOne
	@JoinColumn(name = "id_profissional", referencedColumnName = "id", nullable = false)
	private Profissional profissional;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "id_restaurante", referencedColumnName = "id", nullable = false)
	private Restaurante restaurante;

	public Voto() {
		data = DataUteis.getMinimizedTime(new Date());
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {
		return id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((profissional == null) ? 0 : profissional.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voto other = (Voto) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (profissional == null) {
			if (other.profissional != null)
				return false;
		} else if (!profissional.equals(other.profissional))
			return false;
		return true;
	}
}
