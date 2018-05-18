package com.anythy.app.annotation;

import com.anythy.app.annotation.DataSource;
import com.anythy.app.config.DataSourceHolder;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(-1)
@Aspect
public class DataSourceAop {

    @Before("@annotation(dataSource)")
    public void before(DataSource dataSource){
        DataSourceHolder.chooseDataSource(dataSource.value());
    }

    @After("@annotation(dataSource)")
    public void after(DataSource dataSource){
        DataSourceHolder.chooseDefaultDataSource();
    }
}
