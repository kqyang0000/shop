package com.imocc.config.dao;

import com.imocc.util.DESUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;

/**
 * <p>配置dataSource到ioc容器里面
 *
 * @author kqyang
 * @version 1.0
 * @date 2019/5/30 14:02
 */
@Configuration
@MapperScan("com.imocc.dao")
public class DataSourceConfiguration {
    @Value("${jdbc.driver}")
    private String jdbcDriver;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUsername;
    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Bean(name = "dataSource")
    public ComboPooledDataSource createDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(jdbcDriver);
        dataSource.setJdbcUrl(DESUtil.getDecryptString(jdbcUrl));
        dataSource.setUser(DESUtil.getDecryptString(jdbcUsername));
        dataSource.setPassword(DESUtil.getDecryptString(jdbcPassword));
        // c3p0最大连接池数量/最小连接池数量
        dataSource.setMaxPoolSize(30);
        dataSource.setMinPoolSize(10);
        // 关闭连接后不自动提交
        dataSource.setAutoCommitOnClose(false);
        // 连接超时时间
        dataSource.setCheckoutTimeout(10000);
        // 连接失败重试此时
        dataSource.setAcquireRetryAttempts(2);
        return dataSource;
    }
}