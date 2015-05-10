package com.jsursh.security.admin.dao;

import java.io.Serializable;
import java.util.List;

/**
 * hibernate base dao
 * 
 * @author sunburst
 *
 * @param <?> entity
 */
public interface BaseHibernateDao<T> {
	
	Serializable save(T o);

	void delete(T o);

	void update(T o);

	void saveOrUpdate(T o);

	List<T> find(String hql);
	
	List<T> findSQL(String hql, Class<T> t);
	
	List<T> findSQL(String hql);

	List<T> find(String hql, Object[] param);

	List<T> find(String hql, List<Object> param);

	/**
	 * @param page
	 *            which pag to display
	 * @param rows
	 *            how many records in each page
	 */
	List<T> find(String hql, Object[] param, Integer page, Integer rows);

	List<T> find(String hql, List<Object> param, Integer page, Integer rows);

	T get(Class<T> c, Serializable id);

	T get(String hql, Object[] param);

	T get(String hql, List<Object> param);

	Long count(String hql);

	Long count(String hql, Object[] param);

	Long count(String hql, List<Object> param);

	/**
	 * @return number of results
	 */
	Integer executeHql(String hql);

	Integer executeHql(String hql, Object[] param);

	Integer executeHql(String hql, List<Object> param);

}
