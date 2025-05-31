package com.test.device.hexagonal.infrastructure.adapters.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.test.device.hexagonal.domain.service.DeviceService;
import com.test.device.hexagonal.infrastructure.adapters.output.persistence.DevicePersistenceAdapter;

/**
 * Configuracion BEANS
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public DeviceService deviceService(DevicePersistenceAdapter devicePersistenceAdapter) {
        return new DeviceService(devicePersistenceAdapter);
    }

}
