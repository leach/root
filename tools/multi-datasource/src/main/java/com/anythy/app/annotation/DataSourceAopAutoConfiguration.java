package com.anythy.app.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * 注解@DataSource自动配置类
 */
@Configuration
@EnableAspectJAutoProxy
@Import(DataSourceAop.class)
public class DataSourceAopAutoConfiguration {
}
