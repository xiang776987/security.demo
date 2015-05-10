package com.jsursh.security.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jsursh.security.admin.entity.TestEntity;

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

}
