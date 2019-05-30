package com.imocc.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * <p>配置事物管理器
 * <p>继承TransactionManagementConfigurer 是因为开启annotation-driven
 * <p>使用注解@EnableTransactionManagement 开启事务支持后在service方法上添加@Transactional 注解即可
 *
 * @author kqyang
 * @version 1.0
 * @date 2019/5/30 23:32
 */
@Configuration
@EnableTransactionManagement
public class TransactionManagementConfiguration implements TransactionManagementConfigurer {
    @Autowired
    private DataSource dataSource;

    /**
     * <p>关于事务管理，需要返回PlatformTransactionManager的实现
     *
     * @author kqyang
     * @version 1.0
     * @date 2019/5/30 23:41
     */
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}