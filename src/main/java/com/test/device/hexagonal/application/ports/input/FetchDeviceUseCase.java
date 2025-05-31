package com.test.device.hexagonal.application.ports.input;

import java.util.List;

import com.test.device.hexagonal.domain.model.Device;

public interface FetchDeviceUseCase {

	List<Device> getAllDevices();
	Device getDeviceById(Long id);
	List<Device> getDeviceByName(String name);
	List<Device> getDeviceByBrand(String brand);
	
}
