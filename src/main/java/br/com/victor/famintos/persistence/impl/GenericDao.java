package br.com.victor.famintos.persistence.impl;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import br.com.victor.famintos.exception.DatabaseException;
import br.com.victor.famintos.model.IModel;
import br.com.victor.famintos.persistence.IDao;

/**
 * @author Victor H. Colombo
 * @since 27/01/2017
 */
public class GenericDao<T extends IModel> implements IDao<T> {

	@PersistenceContext(unitName = "famintosDS")
	private EntityManager em;

	private final Class<T> type;

	public GenericDao(Class<T> clazz) {
		type = clazz;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T persist(T entity) throws Exception {
		try {
			entity = em.merge(entity);
			return entity;
		} catch (final ConstraintViolationException e) {
			throw new DatabaseException(getMensagemValidacao(e));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(T entity) throws DatabaseException {
		try {
			entity = em.merge(entity);
			em.remove(entity);
		} catch (final ConstraintViolationException e) {
			throw new DatabaseException(getMensagemValidacao(e));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T findById(Object primaryKey) throws DatabaseException {
		return em.find(getTypeClass(), primaryKey);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<T> findAll() throws DatabaseException {
		try {
			final CriteriaQuery<T> query = getCriteriaQuery();

			final Metamodel model = em.getMetamodel();
			final EntityType<T> type = model.entity(getTypeClass());
			query.from(type);
			final List<T> retorno = em.createQuery(query).getResultList();
			return retorno;
		} catch (final Exception e) {
			throw new DatabaseException(e);
		}
	}

	private Class<T> getTypeClass() {
		return type;
	}

	/**
	 * Monta a mensagem de validação do objeto
	 *
	 * @param e
	 * @return
	 */
	private String getMensagemValidacao(ConstraintViolationException e) {
		final Iterator<ConstraintViolation<?>> iterator = e.getConstraintViolations().iterator();
		final StringBuilder builder = new StringBuilder();
		while (iterator.hasNext()) {
			final ConstraintViolation<?> cv = iterator.next();
			builder.append("A proprietade: [" + cv.getPropertyPath() + "] do Objeto:[ "
					+ cv.getRootBeanClass().getSimpleName() + "] não é válida, mensagem:[" + cv.getMessage()
					+ "], valor recebido:[" + cv.getInvalidValue() + "]");
			if (iterator.hasNext()) {
				builder.append(" | ");
			}
		}
		return builder.toString();
	}

	protected CriteriaQuery<T> getCriteriaQuery() {
		final CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		final CriteriaQuery<T> query = criteriaBuilder.createQuery(getTypeClass());
		return query;
	}

	/**
	 * Retorna o entityManager da aplicaÃ§ão
	 */
	public EntityManager getEm() {
		return em;
	}
}
