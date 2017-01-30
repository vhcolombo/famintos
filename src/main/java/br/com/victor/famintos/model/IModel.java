package br.com.victor.famintos.model;

import java.io.Serializable;

/**
 * @author Victor H. Colombo
 * @since 27/01/2017
 */
public interface IModel extends Serializable {

	/**
	 * Retorna o ID do bean
	 * @return
	 */
	Long getId();

	/**
	 * Seta o ID do bean
	 * @param id
	 */
	void setId(Long id);

}
