package com.jsursh.security.admin.test;

import java.util.List;

import net.sf.json.JSONArray;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.jsursh.security.admin.entity.TestEntity;
import com.jsursh.security.admin.service.TestEntityService;

@TransactionConfiguration(defaultRollback=false)
public class TestEntityServiceTest extends BaseTest {

	@Autowired
	private TestEntityService testEntityService;
	
	@Test
	public void testSave(){
		testEntityService.save("mybatis save");
		
		for (int i=0; i < 20; i++) {
			testEntityService.save("mybatis-"+i);
		}
	}
	
	@Test
	public void find(){
		List<TestEntity> testEntitys = testEntityService.findByTextLike("0");
		Assert.assertNotNull(testEntitys);
		logger.info("find : "+JSONArray.fromObject(testEntitys).toString());
	}
	
}
