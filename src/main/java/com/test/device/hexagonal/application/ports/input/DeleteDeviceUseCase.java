package com.test.device.hexagonal.application.ports.input;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface DeleteDeviceUseCase {
	void deleteDeviceById(Long id) throws NotFoundException;
	
}
