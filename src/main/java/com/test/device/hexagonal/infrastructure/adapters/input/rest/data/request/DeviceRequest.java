package com.test.device.hexagonal.infrastructure.adapters.input.rest.data.request;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.test.device.hexagonal.domain.model.enums.StateEnum;
import com.test.device.hexagonal.infrastructure.adapters.output.persistence.entity.CustomJsonDateDeserializer;

import jakarta.validation.constraints.*;
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
public class DeviceRequest {

    private Long id;
    
    @NotBlank
    private String name;
    
    @NotBlank
    private String brand;
    
    @NotNull
    private StateEnum state;
    
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Date creationTime;
    
}

