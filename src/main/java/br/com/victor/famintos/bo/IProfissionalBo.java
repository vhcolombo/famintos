package br.com.victor.famintos.bo;

import javax.ejb.Remote;

import br.com.victor.famintos.model.impl.Profissional;

/**
 * @author Victor H. Colombo
 * @since 28/01/2017
 */
@Remote
public interface IProfissionalBo {

	/**
	 * Método responsável por buscar o profissional na base, caso não exista cria o mesmo
	 * 
	 * @throws Exception
	 */
	Profissional buscaAdicionaProfissional(Profissional profissional) throws Exception;
}