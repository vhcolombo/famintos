package br.com.victor.famintos.bo.impl;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.victor.famintos.bo.IProfissionalBo;
import br.com.victor.famintos.model.impl.Profissional;

import br.com.victor.famintos.ejb.impl.ProfissionalSessionBean;

/**
 * @author Victor H. Colombo
 * @since 29/01/2017
 */
@Stateless
@LocalBean
public class ProfissionalBoBean implements IProfissionalBo {
	@EJB
	private ProfissionalSessionBean service;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Profissional buscaAdicionaProfissional(Profissional profissional) throws Exception {
		Profissional profissionalBD = service.findByName(profissional.getName());
		if(profissionalBD == null){
			profissionalBD = service.persist(profissional);
		}
		return profissionalBD;
	}

}