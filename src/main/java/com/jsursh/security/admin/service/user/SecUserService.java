package com.jsursh.security.admin.service.user;

import java.util.LinkedList;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jsursh.security.admin.dao.user.SecUserDao;
import com.jsursh.security.admin.entity.SecUser;
import com.jsursh.security.admin.exception.BaseJsrushException;

/**
 * 用户管理
 * @author sunburst
 *
 */
@Service("SecUserService")
@Transactional(readOnly=false)
public class SecUserService {

	@Resource
	private SecUserDao secUserDao;
	
	public void save(SecUser vo) throws BaseJsrushException {
		SecUser secUser =  findByLoginName(vo.getLoginName());
		if (secUser != null) {
			throw new BaseJsrushException("登录名已被使用", -2);
		}
		secUserDao.save(vo);
	}
	
	public void update(SecUser vo) {
		secUserDao.update(vo);
	}
	
	public void updateValid(SecUser vo) {
		secUserDao.updateValid(vo);
	}
	
	public void deleteLogic(String ids) {
		secUserDao.deleteLogic(ids);
	}

	@Transactional(readOnly=true)
	public SecUser login(String loginName, String password) {
		return secUserDao.login(loginName, password);
	}
	
	@Transactional(readOnly=true)
	public Map<String, Object> findPage(Map<String, Object> params, LinkedList<String> orderByKey, Integer pageNo, Integer pageSize) {
		return secUserDao.findPage(params, orderByKey, pageNo, pageSize);
	}
	
	@Transactional(readOnly=true)
	public SecUser findByLoginName(String loginName) {
		return secUserDao.findByLoginName(loginName);
	}
	
}
