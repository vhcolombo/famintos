package br.com.victor.famintos.ejb.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.victor.famintos.bo.impl.VotoBoBean;
import br.com.victor.famintos.ejb.ISessionBeanRemote;
import br.com.victor.famintos.exception.DatabaseException;
import br.com.victor.famintos.model.impl.Voto;
import br.com.victor.famintos.persistence.impl.VotoDao;
import br.com.victor.famintos.util.DataUteis;

/**
 * Session Bean implementation class votoSessionBean
 * 
 * @author Victor H. Colombo
 * @since 29/01/2017
 */
@Stateless
@LocalBean
public class VotoSessionBean implements ISessionBeanRemote<Voto>, VotoSessionBeanRemote {

	@EJB
	private VotoDao dao;

	@EJB
	private VotoBoBean boBean;

	public VotoSessionBean() {
	}

	public VotoSessionBean(VotoDao dao, VotoBoBean boBean) {
		this.dao = dao;
		this.boBean = boBean;
	}

	@Override
	public Voto persist(Voto voto) throws Exception {
		boBean.validaVoto(voto);
		return dao.persist(voto);
	}

	@Override
	public void delete(Voto voto) throws Exception {
		dao.delete(voto);
	}

	@Override
	public Voto findById(long id) throws Exception {
		return dao.findById(id);
	}

	@Override
	public List<Voto> findAll() throws Exception {
		return dao.findAll();
	}

	public Voto findByProfissionalInDate(Voto voto) {
		return dao.findByProfissionalInDate(voto);
	}

	/**
	 * Retorna o restaurante mais votado do dia de hoje
	 * 
	 * @param date
	 * @return
	 * @throws DatabaseException
	 */
	public String getRestauranteDoDia(Date date) throws DatabaseException {
		String retorno = null;
		try {
			Map<String, Map<String, Integer>> resultado = getVotosMap();
			Map<String, Integer> mapDia = resultado.get(DataUteis.formateDate(date, DataUteis.DIAMESANO));
			if (mapDia != null && !mapDia.isEmpty()) {

				List<Map.Entry<String, Integer>> sortedMap = mapDia.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(
					Collectors.toList());
				retorno = sortedMap.get(sortedMap.size() - 1).getKey();
			}
		} catch (Exception e) {
			throw new DatabaseException("Erro ao buscar o restaurante do dia.");
		}

		return retorno;
	}

	/**
	 * Retorna map dos votos
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, Map<String, Integer>> getVotosMap() throws Exception {
		List<Voto> votos = this.findAll();
		Map<String, Map<String, Integer>> resultado = new HashMap<String, Map<String, Integer>>();
		for (Voto voto : votos) {
			if (!resultado.containsKey(DataUteis.formateDate(voto.getData(), DataUteis.DIAMESANO))) {
				resultado.put(DataUteis.formateDate(voto.getData(), DataUteis.DIAMESANO), new HashMap<String, Integer>());
			}
			Map<String, Integer> mapDia = resultado.get(DataUteis.formateDate(voto.getData(), DataUteis.DIAMESANO));
			if (!mapDia.containsKey(voto.getRestaurante().getName())) {
				mapDia.put(voto.getRestaurante().getName(), 0);
			}

			Integer qtd = mapDia.get(voto.getRestaurante().getName());
			mapDia.remove(voto.getRestaurante().getName());
			mapDia.put(voto.getRestaurante().getName(), ++qtd);
		}
		return resultado;
	}
}
