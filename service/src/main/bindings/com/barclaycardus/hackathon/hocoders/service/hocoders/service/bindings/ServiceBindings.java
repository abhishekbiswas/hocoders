package com.barclaycardus.hackathon.hocoders.service.hocoders.service.bindings;


import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

import com.barclaycardus.hackathon.hocoders.service.config.ServiceConfigHolder;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;


/**
 * Created by abhishek on 11/06/16.
 */

@Configuration
public class ServiceBindings {

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

}

