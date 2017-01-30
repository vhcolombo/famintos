package br.com.victor.famintos.ejb;

import java.util.List;

import br.com.victor.famintos.model.IModel;

/**
 * @author Victor H. Colombo
 * @since 29/01/2017
 */
public interface ISessionBeanRemote<T extends IModel> {

	public T persist(T t) throws Exception;

	public void delete(T t) throws Exception;

	public T findById(long id) throws Exception;

	public List<T> findAll() throws Exception;

}
