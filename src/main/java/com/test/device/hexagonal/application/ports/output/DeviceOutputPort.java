package com.test.device.hexagonal.application.ports.output;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.device.hexagonal.infrastructure.adapters.output.persistence.entity.DeviceEntity;

public interface DeviceOutputPort extends JpaRepository<DeviceEntity, Long> {

	List<DeviceEntity> findByName(String name);

	List<DeviceEntity> findByBrand(String brand);
    
}
