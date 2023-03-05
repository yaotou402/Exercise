package com.springboot.ssm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface BaseDao {
	
	List<Map<String,Object>> findObject(Map<String,Object> paramMap);
	
	

}
