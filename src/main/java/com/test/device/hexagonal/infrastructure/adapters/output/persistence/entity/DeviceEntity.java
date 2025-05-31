package com.test.device.hexagonal.infrastructure.adapters.output.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.test.device.hexagonal.domain.model.enums.StateEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "DEVICE")
public class DeviceEntity implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8935030855730408568L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DEVICE")
    private Long id;

    @Column(name = "NAME")
    private String name;
    
    @Column(name = "BRAND")
    private String brand;
    
    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    private StateEnum state;
    
    @Column(name = "CREATIONTIME")
    private Date creationTime;

}
