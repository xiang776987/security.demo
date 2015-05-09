package com.jsursh.security.admin.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/context/spring/commons-context.xml"})
public class BaseTest extends AbstractJUnit4SpringContextTests {

	@Test
	public void testApplicationContextLoad(){
		Assert.assertNotNull(this.applicationContext);
		logger.info("============ load context success! This applicationContext is : "+this.applicationContext.getDisplayName());
	}
	
}
