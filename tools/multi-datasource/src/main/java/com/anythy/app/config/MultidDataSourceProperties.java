package com.anythy.app.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ConfigurationProperties("multid")
@Getter
@Setter
public class MultidDataSourceProperties {
    /**
     * 默认数据源
     */
    private DetailProperties dataSource;
    /**
     * 多数据源
     */
    private List<DetailProperties> dataSources;

    public void check(){
        if(CollectionUtils.isEmpty(dataSources)){
            return;
        }
        /**
         * 多数据源
         */
        Set<String> dataSourceNames = new HashSet<>();

        List<DetailProperties> propertiesList = new ArrayList<>();
        propertiesList.add(dataSource);
        propertiesList.addAll(dataSources);

        for (DetailProperties properties: propertiesList) {
            if(StringUtils.isEmpty(properties.dataSourceName)){
                throw new IllegalArgumentException("存在多个数据源时,每个数据源必须指定dataSourceName属性");
            }
            if(dataSourceNames.contains(properties.dataSourceName)){
                throw new IllegalArgumentException("每个数据源的dataSourceName属性必须唯一");
            }
            dataSourceNames.add(properties.dataSourceName);
        }
    }

    @Setter
    @Getter
    public static class DetailProperties {
        private String dataSourceName;
        private String url;
        private String username;
        private String password;
        private int initialSize = 10;
        private int minIdle = 20;
        private int maxActive = 100;
    }
}
