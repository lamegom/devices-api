package com.test.device.hexagonal.domain.model;

import java.util.Date;

import com.test.device.hexagonal.domain.model.enums.StateEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Device {

    private Long id;

    private String name;
    
    private String brand;
    
    private StateEnum state;
    
    private Date creationTime;

}
