package com.test.device.hexagonal.infrastructure.adapters.output.persistence.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import com.test.device.hexagonal.domain.model.Device;
import com.test.device.hexagonal.infrastructure.adapters.output.persistence.entity.DeviceEntity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeviceMapper {

    private final ModelMapper mapper;

    public Device toDevice(DeviceEntity entity){
        return mapper.map(entity, Device.class);
    }
    
    public DeviceEntity toEntity(Device device){
        return mapper.map(device, DeviceEntity.class);
    }

	public DeviceMapper() {
		this.mapper = new ModelMapper();
	}
	
	public List<Device> listDeviceEntityToListDevice(List<DeviceEntity> lstEntity) {
		return  mapper.map(lstEntity, new TypeToken<List<Device>>() {}.getType());
	}
	
	

}
