package com.anythy.app.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(MultidDataSourceProperties.class)
@Slf4j
public class MuitidDataSourceAutoConfiguration {
    /**
     * 数据源配置属性
     */
    @Autowired
    private MultidDataSourceProperties properties;
    /**
     * 创建的所有数据源
     */
    private Set<DruidDataSource> dataSources = new HashSet<>();

    @Bean(destroyMethod = "")
    public DataSource dataSource() {
        properties.check();
        DataSource defaultDataSource = buildDruidDataSource(properties.getDataSource());

        if (CollectionUtils.isEmpty(properties.getDataSources())) {
            return defaultDataSource;
        }
        // 多数据源处理
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(properties.getDataSource().getDataSourceName(), defaultDataSource);
        for (MultidDataSourceProperties.DetailProperties detailProperties : properties.getDataSources()) {
            targetDataSources.put(detailProperties.getDataSourceName(), buildDruidDataSource(detailProperties));
        }

        MultidDataSource dataSource = new MultidDataSource();
        dataSource.setDefaultTargetDataSource(defaultDataSource);
        dataSource.setTargetDataSources(targetDataSources);

        return dataSource;
    }

    @PreDestroy
    public void destroy() {
        for(DruidDataSource dataSource: dataSources){
            try {
                dataSource.close();
            } catch (Throwable e){
                log.error("关闭druid数据源出错：", e);
            }
        }
    }

    /**
     * 构建druid数据源
     * @param detailProperties
     * @return
     */
    private DruidDataSource buildDruidDataSource(MultidDataSourceProperties.DetailProperties detailProperties){
        DatabaseDriver driver = DatabaseDriver.fromJdbcUrl(detailProperties.getUrl());

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver.getDriverClassName());
        dataSource.setUrl(detailProperties.getUrl());
        dataSource.setUsername(detailProperties.getUsername());
        dataSource.setPassword(detailProperties.getPassword());
        dataSource.setInitialSize(detailProperties.getInitialSize());
        dataSource.setMinIdle(detailProperties.getMinIdle());
        dataSource.setMaxActive(detailProperties.getMaxActive());
        dataSource.setValidationQuery(driver.getValidationQuery());
        try {
            dataSource.setFilters("stat,wall");
            dataSource.init();
        } catch (SQLException e){
            ExceptionUtils.rethrow(e);
        }
        dataSources.add(dataSource);

        return dataSource;
    }

}
