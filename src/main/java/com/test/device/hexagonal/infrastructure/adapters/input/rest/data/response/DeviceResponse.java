package com.test.device.hexagonal.infrastructure.adapters.input.rest.data.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "America/Sao_Paulo")
    private Date creationTime;

   

}
