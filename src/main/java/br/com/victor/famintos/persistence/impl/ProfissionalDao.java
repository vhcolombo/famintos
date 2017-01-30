package br.com.victor.famintos.persistence.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.victor.famintos.model.impl.Profissional;

/**
 * @author Victor H. Colombo
 * @since 28/01/2017
 */
@Stateless
public class ProfissionalDao extends GenericDao<Profissional> {

	public ProfissionalDao() {
		super(Profissional.class);
	}

	/**
	 * Busca o profissional pelo nome
	 * 
	 * @param name
	 * @return
	 */
	public Profissional findByName(String name) {
		TypedQuery<Profissional> query = getEm().createNamedQuery("Profissional.findByName", Profissional.class);
		query.setParameter("name", name);
		List<Profissional> list = query.getResultList();

		return list != null && !list.isEmpty() ? list.get(0) : null;
	}
}
