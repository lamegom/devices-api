package com.test.device;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.test.device.hexagonal.DeviceApplication;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = DeviceApplication.class)
class DeviceApplicationTests {

	@Test
	void contextLoads() {
	}

}
