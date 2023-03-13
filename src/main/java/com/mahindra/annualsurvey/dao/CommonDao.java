package com.mahindra.annualsurvey.dao;


import java.io.Serializable;
import java.util.List;

/**
 * Common Dao Interface, to be implemented by Each class
 * @author admin
 *
 * @param <T>
 */
public interface CommonDao<T extends Serializable> {

	public T findOne(final long id);

	public List<T> findAll();
	
	public List<T> findAll(final String condition);
	
	public List<T> findAll(final String where, final String orderBy);
	
	public List<T> getAllGroupBy(final String where, final String groupBy);

	public T update(final T entity);

	public void delete(final T entity);

	public void deleteById(final long entityId);

	public void create(T entity);
	
	public void createMultiple(List<T> entity);
	
	public List<T> getFindAll(String strQuery);  
	
	public T findOne(final String condition);

	
}
