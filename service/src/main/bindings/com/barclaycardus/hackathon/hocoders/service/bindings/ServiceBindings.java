package com.barclaycardus.hackathon.hocoders.service.bindings;


import com.barclaycardus.hackathon.hocoders.service.dto.CoreRdsDbConfig;
import com.barclaycardus.hackathon.hocoders.service.dto.User;
import com.barclaycardus.hackathon.hocoders.service.rds.ObjectConverter;
import com.barclaycardus.hackathon.hocoders.service.rds.RDSDataAccessObject;
import com.barclaycardus.hackathon.hocoders.service.rds.RDSTableMetaData;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;

import com.barclaycardus.hackathon.hocoders.service.config.ServiceConfigHolder;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * Created by abhishek on 11/06/16.
 */

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class ServiceBindings {

    @Inject
    private ResourcePatternResolver resourcePatternResolver;



    @Bean
    public static File jerseyServiceConfigDir() {
        return new File(System.getProperty("com.barclaycardus.hackathon.hocoders.service.config"));
    }

    @Bean
    public File jerseyServiceConfigFile() {
        return new File(jerseyServiceConfigDir(), "jersey-service-config.json");
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        // Enable DeserializationFeature
        objectMapper.enable(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.enable(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        // Disable DeserializationFeature
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_NULL_FOR_PRIMITIVES);
        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);

        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);

        return objectMapper;
    }

    @Bean(initMethod = "init")
    public ServiceConfigHolder jerseyServiceConfigHolder() throws IOException {
        ServiceConfigHolder serviceConfigHolder = new ServiceConfigHolder();
        serviceConfigHolder.setObjectMapper(objectMapper());
        serviceConfigHolder.setServiceConfigFile(jerseyServiceConfigFile().getAbsolutePath());
        return serviceConfigHolder;
    }

    @Bean
    public DozerBeanMapper dozer() throws Exception {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        List<String> mappingFiles = new ArrayList<>();
        dozerBeanMapper.setMappingFiles(mappingFiles);
        return dozerBeanMapper;
    }

    @Bean
    public ObjectConverter objectConverter() {return new ObjectConverter();}


    @Bean
    CoreRdsDbConfig coreRdsDbConfig() throws IOException {
        return jerseyServiceConfigHolder().getCoreRdsConfig();
    }

    @Bean
    public ComboPooledDataSource rdsDataSource() throws IOException, PropertyVetoException {
        ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
        CoreRdsDbConfig rdsDbConfig = coreRdsDbConfig();
        pooledDataSource.setDriverClass(rdsDbConfig.getDriver());
        pooledDataSource.setJdbcUrl(rdsDbConfig.getUrl());
        pooledDataSource.setUser(rdsDbConfig.getUser());
        pooledDataSource.setPassword(rdsDbConfig.getPassword());
        //pooledDataSource.setMinPoolSize(rdsDbConfig.getMinPoolSize());
        //pooledDataSource.setMaxPoolSize(rdsDbConfig.getMaxPoolSize());
        return pooledDataSource;
    }

    @Bean
    public HibernateJpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean emf() throws IOException, PropertyVetoException {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(rdsDataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "create");
        entityManagerFactoryBean.setJpaProperties(properties);
        String[] packagesToScan = {"com.barclaycardus.hackathon.hocoders.service.dto", "com.barclaycardus.hackathon.hocoders.service.rds"};
        entityManagerFactoryBean.setPackagesToScan(packagesToScan);
        entityManagerFactoryBean.afterPropertiesSet();
        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager() throws IOException, PropertyVetoException {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf().getObject());
        transactionManager.afterPropertiesSet();
        return transactionManager;
    }

    @Bean
    public RDSTableMetaData<User> userRDSTableMetaData() throws Exception {
        return new RDSTableMetaData<>(User.class);
    }

    @Bean
    public RDSDataAccessObject<User> userDataAccessObject() throws Exception {
        return new RDSDataAccessObject(userRDSTableMetaData());
    }

}

