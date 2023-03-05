package com.springboot.ssm.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * springboot测试使用
 * @author Administrator
 *
 */
@Controller
@EnableAutoConfiguration
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    String home() {
        return "Hello ,spring boot!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HelloController.class, args);
        //运行之后在浏览器中访问：http://localhost:8080/hello
    }
    
}