package com.test.device.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.test.device.hexagonal.DeviceApplication;
import com.test.device.hexagonal.domain.model.Device;
import com.test.device.hexagonal.domain.model.enums.StateEnum;
import com.test.device.hexagonal.domain.service.DeviceService;
import com.test.device.hexagonal.infrastructure.adapters.output.persistence.DevicePersistenceAdapter;
import com.test.device.hexagonal.infrastructure.adapters.output.persistence.entity.DeviceEntity;

@SpringBootTest
@ContextConfiguration(classes = DeviceApplication.class)
public class DeviceServiceTest {
	
	@Mock
	DevicePersistenceAdapter deviceRepository;
	
	@InjectMocks
	DeviceService deviceService;

	
	@Test
	void createDeviceTest() {
		
		//arrange
		DeviceEntity device = DeviceEntity.builder(). id(1L).name( "device 1").brand( "device band").state(StateEnum.AVAILABLE).creationTime( new Date()).build();
		Device deviceToSave = Device.builder(). id(null).name( "device 1").brand( "device band").state(StateEnum.AVAILABLE).creationTime( new Date()).build();
		when(deviceRepository.save(any(DeviceEntity.class))).thenReturn(device);

		
		
		//act
		Device deviceSaved = deviceService.createDevice(deviceToSave);
		//assert
		assertNotNull(deviceSaved);
		assertEquals(1L, deviceSaved.getId());
		verify(deviceRepository, times(1)).save(any());
		
	}
	
	@Test
	void updateDeviceTest() {
		
		//arrange
		DeviceEntity device = DeviceEntity.builder(). id(1L).name( "device 1").brand( "device band").state(StateEnum.IN_USE).creationTime( new Date()).build();
		when(deviceRepository.save(any(DeviceEntity.class))).thenReturn(device);
		when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));
		
		//act
		Device deviceToUpdate = Device.builder(). id(1L).name( "device 1").brand( "device band").state(StateEnum.AVAILABLE).creationTime( new Date()).build();
		Device deviceUpdated = deviceService.updateDevice(deviceToUpdate);
		//assert
		assertNotNull(deviceUpdated);
		assertEquals(device.getCreationTime(), deviceUpdated.getCreationTime());
		assertEquals(StateEnum.IN_USE, deviceUpdated.getState());
		verify(deviceRepository, times(1)).save(any());
		verify(deviceRepository, times(1)).findById( 1L);
		
	}
	
	@Test
	void updateDeviceWithStateInUseTest() {
		
		//arrange
		DeviceEntity device = DeviceEntity.builder(). id(1L).name( "device 1").brand( "device brand").state(StateEnum.AVAILABLE).creationTime( new Date()).build();
		when(deviceRepository.save(any(DeviceEntity.class))).thenReturn(device);
		when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));
		
		
		//act

		Device deviceToUpdate = Device.builder(). id(1L).name( "device 2").brand( "device brand 2").state(StateEnum.IN_USE).creationTime( new Date()).build();

		Device deviceUpdated = deviceService.updateDevice(deviceToUpdate);
		//assert
		assertNotNull(deviceUpdated);
		assertEquals(StateEnum.AVAILABLE, deviceUpdated.getState());
		assertEquals("device 1", deviceUpdated.getName());
		assertEquals("device brand", deviceUpdated.getBrand());
		verify(deviceRepository, times(1)).save(any());
		verify(deviceRepository, times(1)).findById( 1L);
		
	}
	
	@Test
	void deleteDeviceById_throwsExceptionIfIDNotFound() {
	    assertThatExceptionOfType(NoSuchElementException.class)
	        .isThrownBy(() -> deviceService.deleteDeviceById(1L))
	        .withMessage("No value present");
	}
	
	@Test
	void deleteDeviceTest() {
		
		//arrange
		DeviceEntity device = DeviceEntity.builder(). id(1L).name( "device 1").brand( "device band").state(StateEnum.AVAILABLE).creationTime( new Date()).build();
		when(deviceRepository.findById(1L)).thenReturn(Optional.of(device)).thenReturn(null);
		Mockito.doNothing().when(deviceRepository).delete(any(DeviceEntity.class));
		
		
		//act
		deviceService.deleteDeviceById(1L);
		//assert
		assertNull(deviceRepository.findById(1L));
		verify(deviceRepository, times(1)).delete(any(DeviceEntity.class));
		verify(deviceRepository, times(2)).findById(1L);
		
	}
	
	@Test
	void deleteDeviceWithStateInUseTest() {
		
		//arrange
		DeviceEntity device = DeviceEntity.builder(). id(1L).name( "device 1").brand( "device band").state(StateEnum.IN_USE).creationTime( new Date()).build();
		when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));
		
		
		//act
		deviceService.deleteDeviceById(1L);
		//assert
		assertNotNull(deviceRepository.findById(1L));
		verify(deviceRepository, times(2)).findById(1L);
		
	}

	@Test
	void fetchAllDevicesTest() {
		
		//arrange
		DeviceEntity device = DeviceEntity.builder(). id(1L).name( "device 1").brand( "device band").state(StateEnum.IN_USE).creationTime( new Date()).build();
		when(deviceRepository.findAll()).thenReturn(List.of(device));
		
		
		//act
		List<Device> list = deviceService.getAllDevices();
		//assert
		assertTrue(list.size() > 0);
		verify(deviceRepository, times(1)).findAll();
		
	}

	
	@Test
	void fetchByIDTest() {
		
		//arrange
		DeviceEntity device = DeviceEntity.builder(). id(1L).name( "device 1").brand( "device band").state(StateEnum.IN_USE).creationTime( new Date()).build();
		when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));
		
		
		//act
		Device fetchedDevice = deviceService.getDeviceById(1L);
		//assert
		assertEquals(fetchedDevice.getName(), "device 1");
		verify(deviceRepository, times(1)).findById(1L);
		
	}
	
	@Test
	void fetchByNameTest() {
		
		//arrange
		DeviceEntity device = DeviceEntity.builder(). id(1L).name( "device 1").brand( "device brand").state(StateEnum.IN_USE).creationTime( new Date()).build();
		when(deviceRepository.findByName("device 1")).thenReturn(List.of(device));
		
		
		//act
		List<Device> devices = deviceService.getDeviceByName("device 1");
		//assert
		assertEquals(devices.getFirst().getBrand(), "device brand");
		verify(deviceRepository, times(1)).findByName("device 1");
		
	}
	
	@Test
	void fetchByBrandTest() {
		
		//arrange
		DeviceEntity device = DeviceEntity.builder(). id(1L).name( "device 1").brand( "device brand").state(StateEnum.IN_USE).creationTime( new Date()).build();
		when(deviceRepository.findByBrand("device brand")).thenReturn(List.of(device));
		
		
		//act
		List<Device> devices = deviceService.getDeviceByBrand("device brand");
		//assert
		assertEquals(devices.getFirst().getName(), "device 1");
		verify(deviceRepository, times(1)).findByBrand("device brand");
		
	}

	
}
