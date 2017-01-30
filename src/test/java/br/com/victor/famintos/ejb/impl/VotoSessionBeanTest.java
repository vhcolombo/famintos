package br.com.victor.famintos.ejb.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.victor.famintos.bo.impl.VotoBoBean;
import br.com.victor.famintos.model.impl.Profissional;
import br.com.victor.famintos.model.impl.Restaurante;
import br.com.victor.famintos.model.impl.Voto;
import br.com.victor.famintos.persistence.impl.VotoDao;


/**
 * @author Victor H. Colombo
 * @since 29/01/2017
 */
public class VotoSessionBeanTest {

	private VotoSessionBean votoSessionBean;
	private VotoBoBean votoBoBean;
	private static VotoDao votoDao = Mockito.mock(VotoDao.class);

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		votoBoBean = new VotoBoBean();
		votoSessionBean = new VotoSessionBean(votoDao, votoBoBean);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testResultadoVotacao() throws Exception {
		List<Voto> votos = new ArrayList<Voto>();
		Voto voto1 = new Voto();
		voto1.setProfissional(new Profissional(1l, "Victor"));
		voto1.setRestaurante(new Restaurante(1l, "Restaurante A"));
		votos.add(voto1);

		Voto voto2 = new Voto();
		voto2.setProfissional(new Profissional(1l, "Paulo"));
		voto2.setRestaurante(new Restaurante(1l, "Restaurante A"));
		votos.add(voto2);

		Voto voto3 = new Voto();
		voto3.setProfissional(new Profissional(1l, "Pedro"));
		voto3.setRestaurante(new Restaurante(1l, "Restaurante B"));
		votos.add(voto3);

		Mockito.when(votoDao.findAll()).thenReturn(votos);
		String result = votoSessionBean.getRestauranteDoDia(new Date());
		assertEquals(result, "Restaurante A");
		Mockito.verify(votoDao, Mockito.atLeastOnce());
	}
}
