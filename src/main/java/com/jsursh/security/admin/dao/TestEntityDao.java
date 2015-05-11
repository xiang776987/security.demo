package com.jsursh.security.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jsursh.security.admin.entity.TestEntity;
import com.jsursh.security.admin.util.PageInfo;

/**
 * mybatis interface
 * 
 * @author sunburst
 *
 */
@Repository
public interface TestEntityDao {

	void save(String text);
	
	List<TestEntity> findByTextLike(String text);

	TestEntity get(Long id);

	List<TestEntity> findPageList(@Param("testEntity") TestEntity testEntity, @Param("page") PageInfo pageInfo);
	
	
}
