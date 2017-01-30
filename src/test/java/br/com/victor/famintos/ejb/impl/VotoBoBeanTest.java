package br.com.victor.famintos.ejb.impl;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.victor.famintos.bo.impl.VotoBoBean;
import br.com.victor.famintos.exception.BusinessException;
import br.com.victor.famintos.model.impl.Profissional;
import br.com.victor.famintos.model.impl.Restaurante;
import br.com.victor.famintos.model.impl.Voto;
import br.com.victor.famintos.util.DataUteis;

/**
 * @author Victor H. Colombo
 * @since 29/01/2017
 */
public class VotoBoBeanTest {

	private VotoBoBean votoBoBean;
	private static VotoSessionBean votoSessionBean = Mockito.mock(VotoSessionBean.class);

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		votoBoBean = new VotoBoBean(votoSessionBean);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=BusinessException.class)
	public void testProfissionalVotandoDuasVezes() throws Exception {
		Voto voto1 = new Voto();
		voto1.setProfissional(new Profissional(1l, "Victor"));
		voto1.setRestaurante(new Restaurante(1l, "Restaurante A"));

		Voto voto2 = new Voto();
		voto2.setProfissional(new Profissional(1l, "Victor"));
		voto2.setRestaurante(new Restaurante(2l, "Restaurante B"));
		Mockito.when(votoSessionBean.findByProfissionalInDate(voto2)).thenReturn(voto1);
		
		votoBoBean.validaVoto(voto2);
	}
	
	@Test(expected=BusinessException.class)
	public void testRestauranteEscolhidoDuasVezes() throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);

		Mockito.when(votoSessionBean.getRestauranteDoDia(DataUteis.getMinimizedTime(cal.getTime()))).thenReturn("Restaurante A");

		Voto voto1 = new Voto();
		voto1.setProfissional(new Profissional(1l, "Victor"));
		voto1.setRestaurante(new Restaurante(1l, "Restaurante A"));

		votoBoBean.validaVoto(voto1);
	}
}
