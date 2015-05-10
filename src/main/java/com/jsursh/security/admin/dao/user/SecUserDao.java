package com.jsursh.security.admin.dao.user;

import java.util.LinkedList;
import java.util.Map;

import com.jsursh.security.admin.entity.SecUser;

/**
 * 用户管理
 * @author sunburst
 *
 */
public interface SecUserDao {
	
	void save(SecUser vo);
	
	void update(SecUser vo);
	
	void updateValid(SecUser vo);
	
	void deleteLogic(String ids);

	SecUser login(String loginName, String password);
	
	Map<String, Object> findPage(Map<String, Object> params, LinkedList<String> orderByKey, Integer pageNo, Integer pageSize);
	
	SecUser findByLoginName(String loginName);
	
}
