package com.ming.blog.anno;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * spring为我们提供了AbstractRoutingDataSource，即带路由的数据源。
 * 继承后我们需要实现它的determineCurrentLookupKey()，
 * 该方法用于自定义实际数据源名称的路由选择方法，
 * 由于我们将信息保存到了ThreadLocal中，所以只需要从中拿出来即可。
 * @create 2018-12-28 12:04
 **/
@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        // 直接从ThreadLocal中获取拿到的数据源
        log.info("DynamicDataSource.determineCurrentLookupKey curr data source :" + DynamicDataSourceHolder.getDataSource());
        return DynamicDataSourceHolder.getDataSource();
    }
}
