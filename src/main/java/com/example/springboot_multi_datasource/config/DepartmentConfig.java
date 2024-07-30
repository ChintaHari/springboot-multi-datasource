package com.example.springboot_multi_datasource.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.springboot_multi_datasource.repository.department",
        entityManagerFactoryRef = "departmentEntityManagerFactory",
        transactionManagerRef = "departmentTransactionManager")
public class DepartmentConfig {

    @Bean(name = "departmentDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.department")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "departmentDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.department")
    public DataSource dataSource(@Qualifier("departmentDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "departmentEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("departmentDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.springboot_multi_datasource.entity.department")
                .persistenceUnit("Department")
                .build();
    }

    @Bean(name = "departmentTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("departmentEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
    
}
