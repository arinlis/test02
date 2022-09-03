package com.alinlis.test02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@MapperScan({"com.alinlis.test02.mapper"})
@SpringBootApplication
public class Test02Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(Test02Application.class, args);
        String[] names = run.getBeanDefinitionNames();
        for (String name:names){
            System.out.println(name);
        }
    }

}
