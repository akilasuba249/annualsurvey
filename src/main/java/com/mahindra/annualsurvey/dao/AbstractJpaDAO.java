package com.mahindra.annualsurvey.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 * Abstract DAO to cater basic CRUD Operation. All DAOs will be extending this class, 
 * so as to avoid repetition of code for these operations. 
 * @author admin
 *
 * @param <T>
 */
public abstract class AbstractJpaDAO<T extends Serializable> {

	private Class<T> clazz;

	// Entity Manager dependency Injection
	@PersistenceContext
	private EntityManager entityManager;
	/**
	 * Post construct method to be overridden by subclasses to setup class information. 
	 */
	@PostConstruct
	public void postConstruct(){	
		System.out.println(entityManager);
	}

	public EntityManager getEntityManger(){
		return entityManager;
	}
	/**
	 * Set entity class for which CRUD operations will be executed
	 * @param clazzToSet
	 */
	public final void setClazz(final Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	/**
	 * TO find record by Primary Key
	 * @param id
	 * @return Instance of Entity matching Primary Key.
	 */
	public T findOne(final long id) {
		return entityManager.find(clazz, id);
	}
	
	/**
	 * TO find record by Query
	 * @param id
	 * @return Instance of Entity matching Primary Key.
	 */
	@SuppressWarnings("unchecked")
	public T findOne(final String condition) 
	{
		T t=null;
		try
		{
			return (T) entityManager.createQuery("from " + clazz.getName()+" where "+ condition).getSingleResult();	
		}catch(NoResultException nre)
		{
			System.out.println("@@@@@@@@@@ NoResultException @@@@@@@@@ ");
			return t;
		}catch (EmptyResultDataAccessException e) 
		{
			System.out.println("@@@@@@@@@@ EmptyResultDataAccessException @@@@@@@@@");
			return t;
		}
		//return (T) entityManager.createQuery("from " + clazz.getName()+" where "+ condition).getSingleResult();
	}

	
	/**
	 * Load all the records in table
	 * @return List of all records in a table in form of Objects
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
						
		return entityManager.createQuery("from " + clazz.getName())
				.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<T> findAll(String condition) {
		List<T> resultList = entityManager.createQuery("from " + clazz.getName()+" where "+condition)
				.getResultList();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(String where, String orderBy) {	
		return entityManager.createQuery("select c from " + clazz.getName() + " c " +  (where==null?" ": where) +
									(orderBy==null?" ":" order by " +orderBy))
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAllGroupBy(String where,String groupBy) {	
		return entityManager.createQuery("select c from " + clazz.getName() + " c " +  (where==null?" ": where) +
									(groupBy==null?" ":" group by " +groupBy))
				.getResultList();
	}
	


	
	
	
	/**
	 * To persist an entity in Database table
	 * @param entity
	 */
	public void create(final T entity) {
 		entityManager.persist(entity);
	}
	
	public void createMultiple(final List<T> entity) {
		for(int i=0; i<entity.size();i++){
 		entityManager.persist(entity.get(i));
 		if(i % 1000==0 && i!=0){
 			entityManager.flush();
 			entityManager.clear();
 			}
 		}
	}
	/**
	 * To update an entity
	 * @param entity
	 * @return entity
	 */

	public T update(final T entity) {
		return entityManager.merge(entity);
	}

	/**
	 * To delete an entity from Database
	 * @param entity
	 */
	public void delete(final T entity) {
		entityManager.remove(entity);
	}

	/**
	 * Delete an entity with matching ID
	 * @param entityId
	 */
	public void deleteById(final long entityId) {
		final T entity = findOne(entityId);
		delete(entity);
	}
	
	public EntityManager getEntityManager(){
		return entityManager;
	}
	
	
	
	public List<T> getFindAll(String strQuery)
	{
		Query query=entityManager.createQuery(strQuery);
		List<T> strlist=query.getResultList();
		if(strlist!=null){
		return strlist;
		}else{
			return null;
		}
	}

	
}
