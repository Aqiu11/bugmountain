package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * ClassName: HelloController
 * Package: com.controller
 * Description:
 *
 * @Author AQiu
 * @Create 26/05/2023 20:53
 */
@RestController
public class HelloController {
    private final static  ThreadLocal<String>  THREAD_LOCAL= new ThreadLocal();

    @GetMapping("/hello")
    public String hello(){
        String s = THREAD_LOCAL.get();
        System.out.println(THREAD_LOCAL.get());
        return "hello";
    }

    @PostConstruct
    public void init(){
        THREAD_LOCAL.set("123");
    }
}
