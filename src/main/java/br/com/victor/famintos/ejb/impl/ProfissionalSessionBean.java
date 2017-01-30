package br.com.victor.famintos.ejb.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.victor.famintos.ejb.ISessionBeanRemote;
import br.com.victor.famintos.model.impl.Profissional;
import br.com.victor.famintos.persistence.impl.ProfissionalDao;

/**
 * Session Bean implementation class profissionalSessionBean
 * 
 * @author Victor H. Colombo
 * @since 29/01/2017
 */
@Stateless
@LocalBean
public class ProfissionalSessionBean implements ISessionBeanRemote<Profissional>, ProfissionalSessionBeanRemote {

	@EJB
	private ProfissionalDao dao;

	@Override
	public Profissional persist(Profissional profissional) throws Exception {
		return dao.persist(profissional);
	}

	@Override
	public void delete(Profissional profissional) throws Exception {
		dao.delete(profissional);
	}

	@Override
	public Profissional findById(long id) throws Exception {
		return dao.findById(id);
	}

	@Override
	public List<Profissional> findAll() throws Exception {
		return dao.findAll();
	}

	public Profissional findByName(String name) throws Exception {
		return dao.findByName(name);
	}

}
