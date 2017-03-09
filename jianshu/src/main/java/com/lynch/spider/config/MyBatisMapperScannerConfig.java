/**
 * 
 */
package com.lynch.spider.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 扫描MyBatis所在包
 * @author Lynch
 * @date 2017年3月8日
 */
@Configuration
// 因为这个对象的扫描，需要在MyBatisConfig的后面注入，所以加上下面的注解
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer config = new MapperScannerConfigurer();
        // 获取之前注入的beanName为sqlSessionFactory的对象
        config.setSqlSessionFactoryBeanName("sqlSessionFactory");
        // 指定xml配置文件的路径
        config.setBasePackage("com.lynch.spider.dao");
        return config;
    }
}
