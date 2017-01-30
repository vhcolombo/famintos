package br.com.victor.famintos.bo;

import javax.ejb.Remote;

import br.com.victor.famintos.model.impl.Voto;

/**
 * @author Victor H. Colombo
 * @since 28/01/2017
 */
@Remote
public interface IVotoBo {

	/**
	 * Método responsável por validar o voto
	 * 
	 * @throws Exception
	 */
	void validaVoto(Voto voto) throws Exception;
}