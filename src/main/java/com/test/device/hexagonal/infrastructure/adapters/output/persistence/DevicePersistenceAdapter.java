package com.test.device.hexagonal.infrastructure.adapters.output.persistence;

import org.springframework.stereotype.Repository;

import com.test.device.hexagonal.application.ports.output.DeviceOutputPort;

@Repository
public interface DevicePersistenceAdapter extends DeviceOutputPort {

}
