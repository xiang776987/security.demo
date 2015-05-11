package com.jsursh.security.admin.test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.jsursh.security.admin.entity.SecRole;
import com.jsursh.security.admin.entity.SecUser;
import com.jsursh.security.admin.exception.BaseJsrushException;
import com.jsursh.security.admin.service.role.SecRoleService;
import com.jsursh.security.admin.service.user.SecUserService;

@TransactionConfiguration(defaultRollback=true)
public class SecUserServiceTest extends BaseTest{

	@Autowired
	private SecUserService secUserService;
	
	@Autowired
	private SecRoleService secRoleService;
	
	@Test
	public void testSave() {
		
		List<SecRole> roleList = secRoleService.find();
		SecRole secRole = roleList.get(0);
		
		SecUser vo = new SecUser();
		vo.setFullName("云际");
		vo.setLoginName("sunburst");
		vo.setLonginPassword("1234");
		vo.setPost("happy!");
		vo.setRoleId(secRole.getId());
		vo.setRoleName(secRole.getText());
		
		try {
			secUserService.save(vo);
		} catch (BaseJsrushException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdateValid() {
		SecUser secUser = secUserService.findByLoginName("sunburst");
		Assert.assertNotNull(secUser);
		
		SecUser vo = new SecUser();
		vo.setId(secUser.getId());
		vo.setValid(1);
		secUserService.updateValid(vo);
	}
	
	@Test
	public void testDeleteLogic() {
		String ids = "";
		secUserService.deleteLogic(ids);
	}

	@Test
	public void testLogin() {
		String loginName="sunburst";
		String password="1234";
		SecUser secUser = secUserService.login(loginName, password);
		Assert.assertNotNull(secUser);
		logger.info("login user is : "+JSONObject.fromObject(secUser).toString());
	}
	
	@Test
	public void testFindPage() {
		Map<String, Object> params=new HashMap<String, Object>();
		LinkedList<String> orderByKey=new LinkedList<String>();
		Integer pageNo=1;
		Integer pageSize=1;
		Map<String, Object> pageMap = secUserService.findPage(params, orderByKey, pageNo, pageSize);
		
		logger.info(" find page is " + JSONObject.fromObject(pageMap).toString()); 
	}

	@Test
	public void testFindByLoginName() {
		String loginName = "sunburst";
		SecUser secUser = secUserService.findByLoginName(loginName);
		Assert.assertNotNull(secUser);
		logger.info("find by login name is : "+JSONObject.fromObject(secUser).toString());
	}

}
