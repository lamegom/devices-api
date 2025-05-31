package com.test.device.hexagonal.domain.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.test.device.hexagonal.application.ports.input.CreateDeviceUseCase;
import com.test.device.hexagonal.application.ports.input.DeleteDeviceUseCase;
import com.test.device.hexagonal.application.ports.input.FetchDeviceUseCase;
import com.test.device.hexagonal.application.ports.input.UpdateDeviceUseCase;
import com.test.device.hexagonal.domain.model.Device;
import com.test.device.hexagonal.domain.model.enums.StateEnum;
import com.test.device.hexagonal.infrastructure.adapters.output.persistence.DevicePersistenceAdapter;
import com.test.device.hexagonal.infrastructure.adapters.output.persistence.entity.DeviceEntity;
import com.test.device.hexagonal.infrastructure.adapters.output.persistence.mapper.DeviceMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeviceService implements CreateDeviceUseCase, UpdateDeviceUseCase, DeleteDeviceUseCase, FetchDeviceUseCase{

    private final DevicePersistenceAdapter devicePersistenceAdapter;
    
    private final DeviceMapper deviceMapper = new DeviceMapper();

	@Override
	public Device createDevice(Device device) {
		DeviceEntity entity =  devicePersistenceAdapter.save(deviceMapper.toEntity(device));
		return deviceMapper.toDevice(entity);
	}

	@Override
	public Device updateDevice(Device device) {
		try {
		     Device deviceValidated = deviceMapper.toDevice(devicePersistenceAdapter.findById(device.getId()).orElse(null));
			device.setCreationTime(deviceValidated.getCreationTime());
			
			if(deviceValidated.getState().equals(StateEnum.IN_USE)) {
				device.setName(deviceValidated.getName());
				device.setBrand(deviceValidated.getBrand());
				//device.setState(deviceValidated.getState());
			}
			DeviceEntity entity =  devicePersistenceAdapter.save(deviceMapper.toEntity(device));
			return deviceMapper.toDevice(entity);
	} catch (Exception n) {
		 throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Device not found with id: " + device.getId());

	}
	}

	@Override
	public void deleteDeviceById(Long id) {
		Optional<DeviceEntity> device = null;
		
		try {
			device = devicePersistenceAdapter.findById(id);

			 if(device.get().getState().equals(StateEnum.IN_USE)) {
				 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot delete device with state IN_USE");
			 }
			devicePersistenceAdapter.delete(device != null?device.get():null);
		
		} catch (Exception n) {
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Device not found with id: " + id);

		}

		
	}

	@Override
	public List<Device> getAllDevices() {
		return deviceMapper.listDeviceEntityToListDevice(devicePersistenceAdapter.findAll());
	}

	@Override
	public Device getDeviceById(Long id) {
		Optional<DeviceEntity> device = null;
		try {
			device = devicePersistenceAdapter.findById(id);
			return deviceMapper.toDevice(device.get());
		} catch (NoSuchElementException n) {
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Device not found with id: " + id);

		}

	}

	@Override
	public List<Device> getDeviceByName(String name) {
		List<DeviceEntity> devices = devicePersistenceAdapter.findByName(name);

		return deviceMapper.listDeviceEntityToListDevice(devices);
	}

	@Override
	public List<Device> getDeviceByBrand(String brand) {
		List<DeviceEntity> devices = devicePersistenceAdapter.findByBrand(brand);

		return deviceMapper.listDeviceEntityToListDevice(devices);
	}


	
    
}
