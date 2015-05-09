package com.jsursh.security.admin.test;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jsursh.security.admin.dao.JdbcTemplateDemoDao;

public class JdbcTemplateDemoDaoTest extends BaseTest{

	@Autowired
	private JdbcTemplateDemoDao testDao;
	
	@Test
	public void addData(){
		testDao.addData("TD"+ (Math.random()));
	}
	
	@Test
	public void loadData(){
		List<Map<String,Object>> testDataList = testDao.loadTestData();
		Assert.assertNotNull(testDataList);
		logger.info(" testDataList is :"+JSONArray.fromObject(testDataList).toString());
	}
	
}
