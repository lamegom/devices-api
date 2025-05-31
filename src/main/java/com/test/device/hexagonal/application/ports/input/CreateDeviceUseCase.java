package com.test.device.hexagonal.application.ports.input;

import com.test.device.hexagonal.domain.model.Device;

public interface CreateDeviceUseCase {
	Device createDevice(Device device);
	
}
