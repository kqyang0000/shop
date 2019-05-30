package com.imocc.config.dao;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * <p>配置sqlSessionFactory 到ioc容器
 *
 * @author kqyang
 * @version 1.0
 * @date 2019/5/30 19:33
 */
@Configuration
public class SessionFactoryConfiguration {
    @Value("${mybatis_config_file}")
    private String mybatisConfigFile;
    @Value("${mapper_path}")
    private String mapperPath;
    @Value("${type_alias_package}")
    private String typeAliasPackage;

    @Autowired
    private DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        // 添加mybatis-config 扫描包路径
        factoryBean.setConfigLocation(new ClassPathResource(mybatisConfigFile));
        // 添加mapper 扫描路径
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        String locationPattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;
        factoryBean.setMapperLocations(patternResolver.getResources(locationPattern));
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage(typeAliasPackage);
        return factoryBean;
    }
}