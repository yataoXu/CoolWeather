package com.evan.springlearn;

import com.evan.bean.Person;
import com.evan.config.AppConfig;
import com.evan.config.MainConfig;
import com.evan.service.BookService;
import org.junit.Test;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @Description
 * @ClassName SpringSourceTest
 * @Author Evan
 * @date 2020.03.06 22:44
 */
public class SpringSourceTest {

    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            System.out.println(beanName);
        }

    }

    @Test
    public void test02() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("com.evan.dao");
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            System.out.println(beanName);
        }
    }

    @Test
    public void test03() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

        BookService bookService = applicationContext.getBean(BookService.class);
        Resource resource = bookService.getResource();
        System.out.println(resource);


        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            System.out.println(properties.getProperty("person.nickName"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Properties properties = bookService.getProperties();
        System.out.println(properties);
    }

    @Test
    public void TestLiteBeanMode() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Person person = applicationContext.getBean(Person.class);
        System.out.println(person.hashCode());
        applicationContext.close();
    }

    @Test
    public void testCallBack(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

//        String name = applicationContext.getBean(String.class);
//        System.out.println(name);


        System.out.println("-----------------------");


        FactoryBean factoryBean = applicationContext.getBean(FactoryBean.class);
        System.out.println(factoryBean);
        try {
            Object object = factoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Object object1 = factoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }


        applicationContext.close();
    }
}
