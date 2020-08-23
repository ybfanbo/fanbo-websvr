package com.fanbo.config;

import com.jolbox.bonecp.BoneCPDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.fanbo.dao"}, annotationClass = DataSourceExtraDb.class, sqlSessionFactoryRef = "extraSqlSessionFactory")
public class DataSourceExtraDbConfig {

    @Value("${spring.datasource.extra.driver-class-name}")
    private String driverClassName;

    @Autowired
    private Environment environment;

    @Autowired
    private ResourceLoader resourceLoader;

    @Bean(name = "extraDataSource", destroyMethod = "")
    @ConfigurationProperties(prefix = "spring.datasource.extra")
    public DataSource extraDataSource(){
        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(environment.getProperty("spring.datasource.extra.driver-class-name"));
        dataSource.setJdbcUrl(environment.getProperty("spring.datasource.extra.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.extra.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.extra.password"));
        dataSource.setIdleConnectionTestPeriodInMinutes(20);
        dataSource.setIdleMaxAgeInMinutes(1);
        dataSource.setMaxConnectionsPerPartition(20);
        dataSource.setMinConnectionsPerPartition(1);
        dataSource.setPartitionCount(4);
        dataSource.setAcquireIncrement(5);
        dataSource.setStatementsCacheSize(100);
        return dataSource;
    }

    @Bean(name = "extraSqlSessionFactory")
    public SqlSessionFactory extraSqlSessionFactory(@Qualifier("extraDataSource") DataSource extraDataSource)
            throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(extraDataSource());
        return sessionFactoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(extraDataSource());
    }

    @Bean(name = "jdbcExtra")
    @Autowired
    public JdbcTemplate extraJdbcTemplate(@Qualifier("extraDataSource") DataSource extraDataSource) {
        return new JdbcTemplate(extraDataSource);
    }

}
