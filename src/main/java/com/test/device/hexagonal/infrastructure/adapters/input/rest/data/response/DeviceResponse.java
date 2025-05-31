package com.test.device.hexagonal.infrastructure.adapters.input.rest.data.response;

import java.util.Date;
import java.util.List;

import com.test.device.hexagonal.domain.model.enums.StateEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeviceResponse {
    
    private Long id;

    private String name;
    
    private String brand;
    
    private StateEnum state;
    
    private Date creationTime;

   

}
