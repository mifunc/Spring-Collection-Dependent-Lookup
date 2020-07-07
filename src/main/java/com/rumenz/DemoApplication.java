package com.rumenz;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Map;

public class DemoApplication {

    public static void main(String[] args) {
         AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext();
         ac.register(DemoApplication.class);
         ac.refresh();
         // 根据类型（SuperRumenz.class）获取所有Beand的实例
        Map<String, SuperRumenz> map = ac.getBeansOfType(SuperRumenz.class);
        map.forEach((k,v)->{
            System.out.println(String.format("k %s v %s",k,v));
        });
        // 根据类型（SuperRumenz.class）获取所有Bean的名称
        String[] beanNames = ac.getBeanNamesForType(SuperRumenz.class);
        Arrays.asList(beanNames).stream().forEach(System.out::println);

        // 通过注解获取Bean的名称
        String[] annotationBeanNames = ac.getBeanNamesForAnnotation(Bean.class);
        Arrays.asList(annotationBeanNames).stream().forEach(System.out::println);


        // 通过注解获取Bean的名称列表
        Map<String, Object> map1 = ac.getBeansWithAnnotation(Bean.class);

        map1.forEach((k1,v1)->{
            System.out.println(String.format("k1 %s v1 %s",k1,v1));
        });
        // 通过注解和类型获取Bean的实例,双重判定

        Bean bean = ac.findAnnotationOnBean("superRumenz", Bean.class);
        if(bean!=null){
            System.out.println(ac.getType("superRumenz"));
        }
        ac.close();
    }

    @Bean
    public Rumenz rumenz(){
        Rumenz rumenz = new Rumenz();
        rumenz.setId(123);
        rumenz.setName("入门小站");
        return rumenz;
    }

    @Bean
    public SuperRumenz superRumenz(){
        SuperRumenz sr = new SuperRumenz();
        sr.setKey("超级管理员");
        return sr;
    }



}
