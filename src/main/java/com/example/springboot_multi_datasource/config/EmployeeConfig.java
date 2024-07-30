package com.example.springboot_multi_datasource.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.springboot_multi_datasource.repository.employee",
        entityManagerFactoryRef = "employeeEntityManagerFactory",
        transactionManagerRef = "employeeTransactionManager")
public class EmployeeConfig {


    @Primary
    @Bean(name = "employeeDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.employee")
    DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "employeeDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.employee")
    DataSource dataSource
        (@Qualifier("employeeDataSourceProperties") DataSourceProperties dataSourceProperties) {
        
            return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean(name = "employeeEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean entityManagerFactory(
        EntityManagerFactoryBuilder builder,
        @Qualifier("employeeDataSource") DataSource dataSource) {
        
            return builder
                .dataSource(dataSource)
                .packages("com.example.springboot_multi_datasource.entity.employee")
                .persistenceUnit("Employee")
                .build();
    }

    @Primary
    @Bean(name = "employeeTransactionManager")
    public PlatformTransactionManager transactionManager(
        @Qualifier("employeeEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        
            return new JpaTransactionManager(entityManagerFactory);
    }
}
