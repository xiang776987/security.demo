package com.jsursh.security.admin.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.jsursh.security.admin.dao.BaseHibernateDao;

@Repository("baseHibernateDao")
public class BaseHibernateDaoImpl<T> implements BaseHibernateDao<T> {

	@Resource
	private HibernateTemplate hibernateTemplate;

	private Session getCurrentSession() {
		return hibernateTemplate.getSessionFactory().getCurrentSession();
	}
	
	@Override
	public Serializable save(T o) {
		return hibernateTemplate.save(o);
	}

	@Override
	public void delete(T o) {
		hibernateTemplate.delete(o);
	}

	@Override
	public void update(T o) {
		hibernateTemplate.update(o);
	}

	@Override
	public void saveOrUpdate(T o) {
		hibernateTemplate.saveOrUpdate(o);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql) {
		return (List<T>) hibernateTemplate.find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findSQL(String hql, Class<T> T) {
		return this.getCurrentSession().createSQLQuery(hql).addEntity(T).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findSQL(String hql) {
		return this.getCurrentSession().createSQLQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, Object[] param) {
		return (List<T>) hibernateTemplate.find(hql, param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, List<Object> param) {
		return (List<T>) hibernateTemplate.find(hql, param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, Object[] param, Integer page, Integer rows) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query query = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				query.setParameter(i, param[i]);
			}
		}
		return query.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, List<Object> param, Integer page, Integer rows) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query query = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0, len=param.size(); i < len; i++) {
				query.setParameter(i, param.get(i));
			}
		}
		return query.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}
	
	@Override
	public T get(Class<T> c, Serializable id) {
		return (T) hibernateTemplate.load(c, id);
	}

	@Override
	public T get(String hql, Object[] param) {
		List<T> list = find(hql, param);
		if (CollectionUtils.isEmpty(list)){
			return null;
		}
		return (T) list.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(String hql, List<Object> param) {
		List<?> list = find(hql, param);
		if (CollectionUtils.isEmpty(list)){
			return null;
		}
		return (T) list.get(0);
	}

	@Override
	public Long count(String hql) {
		return (Long) this.getCurrentSession().createQuery(hql).uniqueResult();
	}

	@Override
	public Long count(String hql, Object[] param) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0, len = param.length; i < len; i++) {
				query.setParameter(i, param[i]);
			}
		}
		return (Long) query.uniqueResult();
	}

	@Override
	public Long count(String hql, List<Object> param) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0, len = param.size(); i < len; i++) {
				query.setParameter(i, param.get(i));
			}
		}
		return (Long) query.uniqueResult();
	}

	@Override
	public Integer executeHql(String hql) {
		return this.getCurrentSession().createQuery(hql).executeUpdate();
	}

	@Override
	public Integer executeHql(String hql, Object[] param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0, len = param.length; i < len; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.executeUpdate();
	}

	@Override
	public Integer executeHql(String hql, List<Object> param) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0, len = param.size(); i < len; i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.executeUpdate();
	}

}
