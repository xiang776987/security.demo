package com.jsursh.security.admin.service.role.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jsursh.security.admin.dao.BaseHibernateDao;
import com.jsursh.security.admin.entity.SecRole;
import com.jsursh.security.admin.service.role.SecRoleService;

@Service("secRoleService")
public class SecRoleServiceImpl implements SecRoleService {

	@Resource
	BaseHibernateDao<SecRole> baseHibernateDao;
	
	@Override
	public void addRole(SecRole secRole) {
		Date nowDate = new Date();
		secRole.setCreateTime(nowDate);
		secRole.setUpdateTime(nowDate);
		baseHibernateDao.save(secRole);
	}

	@Override
	public void updateRole(SecRole secRole) {
		SecRole dbSec = baseHibernateDao.get(SecRole.class, secRole.getId());
		Date createTime = dbSec.getCreateTime();
		
		BeanUtils.copyProperties(secRole, dbSec);
		
		Date nowDate = new Date();
		dbSec.setUpdateTime(nowDate);
		dbSec.setCreateTime(createTime);
	}
	
	@Override
	public void deleteRole(String ids) {
		if (StringUtils.isNotBlank(ids)) {
			String[] idArray = ids.split(",");
			for (int i = 0, len = idArray.length; i < len; i++) {
				String idStr = idArray[i].trim();
				baseHibernateDao.delete(baseHibernateDao.get(SecRole.class, Long.parseLong(idStr)));
			}
		}
	}

	@Override
	public Map<String, Object> findPage(Map<String, Object> params, Integer pageNo, Integer pageSize) {
		List<Object> paramList = new ArrayList<Object>();
		String whereHql = buildWhere(params, paramList);
		
		List<SecRole> rowList = (List<SecRole>) baseHibernateDao.find("select t "+whereHql, paramList, pageNo, pageSize);
		Long totalCount = baseHibernateDao.count("select count(distinct t.id) "+whereHql, paramList);
		
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("rows", rowList);
		pageMap.put("total", totalCount);
		
		return pageMap;
	}
	
	private String buildWhere(Map<String, Object> params, List<Object> paramList) {
		StringBuilder whereHql = new StringBuilder(" from com.jsursh.security.admin.entity.SecRole t where 1 = 1 ");
		
		Set<String> keySet = params.keySet();
		Iterator<String> it = keySet.iterator();
		String keyValue = null;
		while (it.hasNext()) {
			keyValue = it.next();
			Object mapVal = params.get(keyValue);
			if (mapVal != null) {
				// other condition update
				if (keyValue.equals("minUpdateTime")) {
					whereHql.append(" and t.updateTime").append(" >= ? ");
				} else if (keyValue.equals("maxUpdateTime")) {
					whereHql.append(" and t.updateTime").append(" <= ? ");
				} else {
					whereHql.append(" and t.").append(keyValue).append(" like ? ");
				}
				paramList.add(mapVal);
			}
		}
		return whereHql.toString();
	}

	@Override
	public List<SecRole> find() {
		return (List<SecRole>) baseHibernateDao.find(" from SecRole ");
	}
	
}
