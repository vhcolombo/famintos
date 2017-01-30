package br.com.victor.famintos.persistence;

import java.util.List;

/**
 * @author Victor H. Colombo
 * @since 28/01/2017
 */
public interface IDao<T> {

	/**
	 * M�todo respons�vel por persistir o registro na base de dados
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	T persist(T entity) throws Exception;

	/**
	 * M�todo respons�vel por deletar o registro na base de dados
	 * 
	 * @param entity
	 * @throws Exception
	 */
	void delete(T entity) throws Exception;

	/**
	 * M�todo respons�vel por buscar o registro na base de dados pelo ID
	 * 
	 * @param primaryKey
	 * @return
	 * @throws Exception
	 */
	T findById(Object primaryKey) throws Exception;

	/**
	 * M�todo respons�vel por buscar todos os registros na base de dados
	 * 
	 * @return
	 * @throws Exception
	 */
	List<T> findAll() throws Exception;

}
