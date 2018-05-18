package com.anythy.app.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

/**
 * 多数据源
 */

public class MultidDataSource extends AbstractRoutingDataSource {
    public MultidDataSource(){
        setLenientFallback(false);
    }
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getCurrentDataSource();
    }
}
