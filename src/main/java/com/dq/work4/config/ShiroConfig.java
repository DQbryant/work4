package com.dq.work4.config;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 */
@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> filterMap = new LinkedHashMap<>();
        filterMap.put("/exam/*","authc");
        filterMap.put("/exams","authc");
        filterMap.put("/downloadfile","authc");
        filterMap.put("/downloadfiles","authc");
        filterMap.put("/showfiles","authc");
        filterMap.put("/showfile","authc");
        filterMap.put("/deletefile","authc");
        filterMap.put("/deletefiles","authc");
        filterMap.put("/examine","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    //DefaultSecurityManager
    @Bean(name="securityManager")
    public DefaultWebSecurityManager defaultSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(userRealm);
        return defaultSecurityManager;
    }

    //创建Realm对象,自定义对象
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}