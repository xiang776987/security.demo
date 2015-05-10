package com.jsursh.security.admin.service.role;

import java.util.List;
import java.util.Map;

import com.jsursh.security.admin.entity.SecRole;

/**
 * role service
 * @author sunburst
 *
 */
public interface SecRoleService {

	void addRole(SecRole secRole);
	
	void updateRole(SecRole secRole);
	
	void deleteRole(String ids);

	Map<String, Object> findPage(Map<String, Object> params, Integer pageNo, Integer pageSize);
	
	List<SecRole> find();
	
}
