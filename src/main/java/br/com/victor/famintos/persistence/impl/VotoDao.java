package br.com.victor.famintos.persistence.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.victor.famintos.model.impl.Voto;

/**
 * @author Victor H. Colombo
 * @since 28/01/2017
 */
@Stateless
public class VotoDao extends GenericDao<Voto> {

	public VotoDao() {
		super(Voto.class);
	}

	/**
	 * Busca o voto pelo profissional e data
	 * 
	 * @param name
	 * @return
	 */
	public Voto findByProfissionalInDate(Voto voto) {
		TypedQuery<Voto> query = getEm().createNamedQuery("Voto.findByProfissionalInDate", Voto.class);
		query.setParameter("profissionalId", voto.getProfissional().getId());
		query.setParameter("data", voto.getData());
		List<Voto> list = query.getResultList();

		return list != null && !list.isEmpty() ? list.get(0) : null;
	}
}
