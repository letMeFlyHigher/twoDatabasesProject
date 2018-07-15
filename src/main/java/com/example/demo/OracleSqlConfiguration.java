package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef="oraclesqlEntityManager",
        transactionManagerRef="oraclesqlTransactionManager",
        basePackages=""
)
public class OracleSqlConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.oraclesql.datasource")
    public DataSource oraclesqlDataSource(){
        return DataSourceBuilder
                .create()
                .build();
    }

    @Primary
    @Bean(name="oraclesqlEntityManager")
    public LocalContainerEntityManagerFactoryBean oraclesqlEntityManagerFactory(EntityManagerFactoryBuilder builder){
       return builder
                .dataSource(oraclesqlDataSource())
                .packages("")
                .persistenceUnit("oraclesqlPU")
                .build();
    }

    @Primary
    @Bean(name="oraclesqlTransacationManager")
    public PlatformTransactionManager oraclesqlTransacationManager(@Qualifier("oraclesqlEntityManager")) EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }
}
