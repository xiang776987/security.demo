package com.jsursh.security.admin.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.CollectionUtils;

import com.jsursh.security.admin.entity.SecRole;
import com.jsursh.security.admin.service.role.SecRoleService;

@TransactionConfiguration(defaultRollback=true)
public class SecRoleServiceTest extends BaseTest {

	@Autowired
	private SecRoleService secRoleService;
	
	@Test
	public void testFindPage() {
		Map<String, Object> params=new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			params.put("minUpdateTime", sdf.parse("2015-05-10 01:10:10"));
			params.put("maxUpdateTime", sdf.parse("2015-05-10 14:10:10"));
		} catch (ParseException e) {
			logger.error("日期格式错误：", e);
			return;
		}

		Integer pageNo=1;
		Integer pageSize=10;
		Map<String, Object> page = secRoleService.findPage(params, pageNo, pageSize);
		logger.info("page obj is : " + JSONObject.fromObject(page));
	}
	
	@Test
	public void testAdd() {
		SecRole secRole = new SecRole();
		secRole.setCode("CTO");
		secRole.setText("首席执行官");
		secRole.setRemark("测试");
		secRoleService.addRole(secRole);
		
		for (int i = 0, len = 20; i < len; i++) {
			SecRole role = new SecRole();
			role.setCode("CODE-"+i);
			role.setText("首席执行码"+i);
			role.setRemark("测试"+i);
			secRoleService.addRole(role);
				
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testUpdate() {
		Integer pageNo=1;
		Integer pageSize=1;
		Map<String, Object> params=new HashMap<String, Object>();
		Map<String, Object> page = secRoleService.findPage(params, pageNo, pageSize);
		logger.info("load update page list obj is : " + JSONObject.fromObject(page));
		
		List<SecRole> rowList = (List<SecRole>) page.get("rows");
		SecRole secRole = rowList.get(0);
		
		Long id = secRole.getId();
		
		SecRole vo = new SecRole();
		vo.setId(id);
		vo.setCode("update-CTO");
		vo.setText("update-首席执行官");
		vo.setRemark("update-更新");
		
		secRoleService.updateRole(vo);
		
		page = secRoleService.findPage(params, pageNo, pageSize);
		logger.info("load update page list obj is : " + JSONObject.fromObject(page));
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testDelete() {
		Integer pageNo=3;
		Integer pageSize=10;
		Map<String, Object> params=new HashMap<String, Object>();
		Map<String, Object> page = secRoleService.findPage(params, pageNo, pageSize);
		logger.info("load delete page list obj is : " + JSONObject.fromObject(page));
		
		List<SecRole> rowList = (List<SecRole>) page.get("rows");
		if (CollectionUtils.isEmpty(rowList)) {
			logger.warn("删除：：无可操作数据");
			return;
		}
		
		String ids = "";
		for (int i = 0 ; i < 5; i++) {
			SecRole secRole = rowList.get(i);
			Long id = secRole.getId();
			ids += ","+id;
		}
		secRoleService.deleteRole(ids.substring(1));
		
		page = secRoleService.findPage(params, pageNo, pageSize);
		logger.info("load delete page list obj is : " + JSONObject.fromObject(page));
		
	}
	
	@Test
	public void testFind(){
		List<SecRole> rowList = secRoleService.find();
		logger.info("load find  list obj is : " + JSONArray.fromObject(rowList).toString());
	}
	
}
