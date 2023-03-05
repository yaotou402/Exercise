package com.springboot.ssm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.ssm.service.BaseService;

@Controller
@RequestMapping(value="/base")
public class BaseController {
	
    @Value("${user.username}")  
    private String username;  
  
    @Value("${user.age}")  
    private Long age; 
    
    @Autowired
    @Qualifier("baseService") 
    private BaseService baseService;

	/**
	 * 测试
	 * @param paramMap
	 * @return
	 */
    @ResponseBody
	@RequestMapping(value="/test")
	public Map<String,Object> test(@RequestParam Map<String,Object> paramMap){
		
		paramMap.put("name", username);
		
		paramMap.put("age", age);
		
		paramMap.put("result", "suc");
		
		return paramMap;
	}
    
    @ResponseBody
	@RequestMapping(value="/findObject")
	public Map<String,Object> findObject(@RequestParam Map<String,Object> paramMap){
    	
		return baseService.findObject(paramMap);
	}
	
	
}
