package com.jsursh.security.admin.dao.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.jsursh.security.admin.dao.BaseHibernateDao;
import com.jsursh.security.admin.entity.SecUser;

@Repository("secUserDao")
public class SecUserDaoImpl  implements SecUserDao {

	@Resource
	private BaseHibernateDao<SecUser> baseHibernateDao;
	
	@Override
	public void save(SecUser vo) {
		vo.setRegisterTime(new Date());
		baseHibernateDao.save(vo);
	}

	
	@Override
	public void update(SecUser vo) {
		SecUser secUser = baseHibernateDao.get(SecUser.class, vo.getId());
		secUser.setFullName(vo.getFullName());
		secUser.setPost(vo.getPost());
		baseHibernateDao.update(secUser);
	}

	
	@Override
	public void updateValid(SecUser vo) {
		SecUser secUser = baseHibernateDao.get(SecUser.class, vo.getId());
		secUser.setValid(vo.getValid());
		baseHibernateDao.update(secUser);
	}

	
	@Override
	public void deleteLogic(String ids) {
		if (StringUtils.isNotBlank(ids)) {
			String[] idArray = ids.split(",");
			for (int i=0, len=idArray.length; i < len; i++) {
				SecUser vo=new SecUser();
				vo.setId(Long.parseLong(idArray[i].trim()));
				vo.setValid(2);
				updateValid(vo);
			}
		}
	}

	@Override
	public SecUser login(String loginName, String password) {
		String hql = "select t from SecUser t where t.loginName = ? and t.longinPassword = ? "; 
		Object[] param=new Object[]{loginName, password};
		List<SecUser> resultList = baseHibernateDao.find(hql, param);
		if (CollectionUtils.isEmpty(resultList))
			return null;
		return resultList.get(0);
	}

	@Override
	public Map<String, Object> findPage(Map<String, Object> params, LinkedList<String> orderByKey, Integer pageNo, Integer pageSize) {
		List<Object> paramList = new ArrayList<Object>();
		String whereHql= buildWhere(params, paramList);
		List<SecUser> rowList = baseHibernateDao.find(" select t "+whereHql, paramList, pageNo, pageSize);
		Long totalCount = baseHibernateDao.count("select count(t.id) " +whereHql, paramList);
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("rows", rowList);
		pageMap.put("total", totalCount);
		
		return pageMap;
	}

	private String buildWhere(Map<String, Object> params, List<Object> paramList) {
		StringBuilder whereHql = new StringBuilder(" from SecUser t where t.valid = 1 ");
		Set<String> keySet = params.keySet();
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String keyVal = it.next();
			Object mapVal = params.get(keyVal);
			if (mapVal != null) {
				whereHql.append(" and t.").append(keyVal).append(" like ? ");
				paramList.add("%%"+mapVal+"%%");
			}
		}
		return whereHql.toString();
	}

	@Override
	public SecUser findByLoginName(String loginName) {
		String hql = "select t from SecUser t where t.loginName = ? ";
		List<SecUser> resultList = baseHibernateDao.find(hql, new Object[]{loginName});
		if (CollectionUtils.isEmpty(resultList))
			return null;
		return resultList.get(0);
	}
	
}
