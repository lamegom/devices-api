package com.test.device.hexagonal.application.ports.input;

import com.test.device.hexagonal.domain.model.Device;

public interface UpdateDeviceUseCase {
	Device updateDevice(Device device);
	
}
