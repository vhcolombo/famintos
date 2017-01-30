package br.com.victor.famintos.bo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.victor.famintos.bo.IVotoBo;
import br.com.victor.famintos.ejb.impl.VotoSessionBean;
import br.com.victor.famintos.exception.BusinessException;
import br.com.victor.famintos.model.impl.Voto;
import br.com.victor.famintos.util.DataUteis;

/**
 * @author Victor H. Colombo
 * @since 29/01/2017
 */
@Stateless
@LocalBean
public class VotoBoBean implements IVotoBo {
	@EJB
	private VotoSessionBean service;

	public VotoBoBean() {
	}

	public VotoBoBean(VotoSessionBean votoSessionBean) {
		this.service = votoSessionBean;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validaVoto(Voto voto) throws Exception {
		Voto votoExistente = service.findByProfissionalInDate(voto);
		if (votoExistente != null) {
			throw new BusinessException("Um profissional só pode votar em um restaurante por dia.");
		}

		List<String> restaurantesSemana = new ArrayList<String>();
		Date dtaAtual = DataUteis.getMinimizedTime(new Date());
		for (Date date : DataUteis.listaSemana(dtaAtual)) {
			if (date.equals(dtaAtual)) {
				continue;
			}
			restaurantesSemana.add(service.getRestauranteDoDia(date));
		}
		if (restaurantesSemana.contains(voto.getRestaurante().getName())) {
			throw new BusinessException("O mesmo restaurante não pode ser escolhido mais de uma vez durante a semana.");
		}
	}

}