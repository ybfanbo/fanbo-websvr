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
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.fanbo.dao"}, annotationClass = DataSourceWebsvrDb.class, sqlSessionFactoryRef = "websvrSqlSessionFactory")
public class DataSourceWebsvrDbConfig {

    @Value("${spring.datasource.websvr.driver-class-name}")
    private String driverClassName;

    @Autowired
    private Environment environment;

    @Autowired
    private ResourceLoader resourceLoader;

    @Primary
    @Bean(name = "websvrDataSource", destroyMethod = "")
    @ConfigurationProperties(prefix = "spring.datasource.websvr")
    public DataSource websvrDataSource(){
        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(environment.getProperty("spring.datasource.websvr.driver-class-name"));
        dataSource.setJdbcUrl(environment.getProperty("spring.datasource.websvr.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.websvr.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.websvr.password"));
        dataSource.setIdleConnectionTestPeriodInMinutes(20);
        dataSource.setIdleMaxAgeInMinutes(1);
        dataSource.setMaxConnectionsPerPartition(20);
        dataSource.setMinConnectionsPerPartition(1);
        dataSource.setPartitionCount(4);
        dataSource.setAcquireIncrement(5);
        dataSource.setStatementsCacheSize(100);
        return dataSource;
    }

    @Primary
    @Bean(name = "websvrSqlSessionFactory")
    public SqlSessionFactory websvrSqlSessionFactory(@Qualifier("websvrDataSource") DataSource sboxDataSource)
            throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(websvrDataSource());
        return sessionFactoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(websvrDataSource());
    }

    @Bean(name = "jdbcWebsvr")
    @Autowired
    public JdbcTemplate websvrJdbcTemplate(@Qualifier("websvrDataSource") DataSource websvrDataSource) {
        return new JdbcTemplate(websvrDataSource);
    }


}

