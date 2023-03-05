package com.springboot.ssm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.springboot.ssm.dao.BaseDao;
import com.springboot.ssm.service.BaseService;

@Service("baseService")
public class BaseServiceImpl implements BaseService {
	
	@Autowired
	//@Qualifier("baseDao")
	private BaseDao baseDao;

	@Override
	public Map<String, Object> findObject(Map<String,Object> paramMap) {
		List<Map<String,Object>> dataList = baseDao.findObject(paramMap);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		resultMap.put("data", dataList);
		return resultMap;
	}

}
