package com.springboot.ssm;  
  
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;  
import org.springframework.boot.autoconfigure.SpringBootApplication;  
import org.springframework.transaction.annotation.EnableTransactionManagement;  
  
@SpringBootApplication
@EnableTransactionManagement  
//@ComponentScan("com.springboot.ssm.controller") 
@MapperScan("com.springboot.ssm.dao")  
  
public class ApplicationStart {  
  
	
    public static void main(String[] args) {  
        SpringApplication.run(ApplicationStart.class, args);  
    }  
}  