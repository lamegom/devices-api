package com.test.device.hexagonal.infrastructure.adapters.input;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.device.hexagonal.application.ports.input.CreateDeviceUseCase;
import com.test.device.hexagonal.application.ports.input.DeleteDeviceUseCase;
import com.test.device.hexagonal.application.ports.input.FetchDeviceUseCase;
import com.test.device.hexagonal.application.ports.input.UpdateDeviceUseCase;
import com.test.device.hexagonal.domain.model.Device;
import com.test.device.hexagonal.infrastructure.adapters.input.rest.data.request.DeviceRequest;
import com.test.device.hexagonal.infrastructure.adapters.input.rest.data.response.DeviceResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/devices")
@RequiredArgsConstructor
public class DeviceRestAdapter {
    
    private final CreateDeviceUseCase createDeviceUseCase;
    private final DeleteDeviceUseCase deleteDeviceUseCase;
    private final UpdateDeviceUseCase updateDeviceUseCase;
    private final FetchDeviceUseCase fecthDeviceUseCase;

    private ModelMapper mapper = new ModelMapper();
    
    @Operation(summary = "Get all devices")
    @ApiResponses(value = { 
      @ApiResponse(responseCode = "200", description = "Found the devices", 
        content = { @Content(mediaType = "application/json", 
          schema = @Schema(implementation = Device.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid call", 
        content = @Content), 
      @ApiResponse(responseCode = "404", description = "Devices not found", 
        content = @Content) })
    @GetMapping
    public ResponseEntity<List<DeviceResponse>> getDevices(){
        List<Device> devices = fecthDeviceUseCase.getAllDevices();
        // Domain to response
        List<DeviceResponse> devicesResponse = devices.stream()
                .map(device -> mapper.map(device, DeviceResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(devicesResponse, HttpStatus.OK);
    }
    
    @Operation(summary = "Get a device by its id")
    @ApiResponses(value = { 
      @ApiResponse(responseCode = "200", description = "Found the device", 
        content = { @Content(mediaType = "application/json", 
          schema = @Schema(implementation = Device.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
        content = @Content), 
      @ApiResponse(responseCode = "404", description = "Device not found", 
        content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<DeviceResponse> getDeviceById(@Parameter(description = "id of device to be searched")  @PathVariable("id") Long id){
        DeviceResponse device = mapper.map(fecthDeviceUseCase.getDeviceById(id), DeviceResponse.class);

        return new ResponseEntity<>(device, HttpStatus.OK);
    }
    
    @Operation(summary = "Get a device by its name")
    @ApiResponses(value = { 
      @ApiResponse(responseCode = "200", description = "Found the device", 
        content = { @Content(mediaType = "application/json", 
          schema = @Schema(implementation = Device.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid name supplied", 
        content = @Content), 
      @ApiResponse(responseCode = "404", description = "Device not found", 
        content = @Content) })
    @GetMapping("/name/{name}")
    public ResponseEntity<List<DeviceResponse>> getDeviceByName(@Parameter(description = "name of device to be searched") @PathVariable("name") String name){
    	List<DeviceResponse> devices = mapper.map(fecthDeviceUseCase.getDeviceByName(name), new TypeToken<List<DeviceResponse>>() {}.getType());

        return new ResponseEntity<>(devices, HttpStatus.OK);
    }
    
    @Operation(summary = "Get a device by its brand")
    @ApiResponses(value = { 
      @ApiResponse(responseCode = "200", description = "Found the device", 
        content = { @Content(mediaType = "application/json", 
          schema = @Schema(implementation = Device.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid brand supplied", 
        content = @Content), 
      @ApiResponse(responseCode = "404", description = "Device not found", 
        content = @Content) })
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<DeviceResponse>> getDeviceByBrand(@Parameter(description = "brand of device to be searched") @PathVariable("brand") String brand){
    	List<DeviceResponse> devicea = mapper.map(fecthDeviceUseCase.getDeviceByBrand(brand), new TypeToken<List<DeviceResponse>>() {}.getType());

        return new ResponseEntity<>(devicea, HttpStatus.OK);
    }
    
    @Operation(summary = "Create a device")
    @ApiResponses(value = { 
      @ApiResponse(responseCode = "201", description = "Created the device", 
        content = { @Content(mediaType = "application/json", 
          schema = @Schema(implementation = Device.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid device supplied", 
        content = @Content), 
      @ApiResponse(responseCode = "404", description = "Device not found", 
        content = @Content) })
    @PostMapping
    public ResponseEntity<DeviceResponse> createDevice(@io.swagger.v3.oas.annotations.parameters.RequestBody(
    	    description = "Device to create", required = true,
    	    content = @Content(mediaType = "application/json",
    	      schema = @Schema(implementation = Device.class),
    	      examples = @ExampleObject(value = "{ \"name\": \"New Device\", \"brand\": \"Brand Name\", \"state\": \"AVAILABLE\", \"creationTime\": \"28/05/2025 03:36:37\" }"))) @Valid  @RequestBody DeviceRequest deviceRequest){
        if(Objects.isNull(deviceRequest.getCreationTime())) {
        	deviceRequest.setCreationTime(new Date());
        }
    	Device device = mapper.map(deviceRequest,  Device.class);
        Device deviceSaved = createDeviceUseCase.createDevice(device);
    	DeviceResponse deviceResponse = mapper.map(deviceSaved, DeviceResponse.class);

        return new ResponseEntity<>(deviceResponse, HttpStatus.CREATED);
    }
    
    @Operation(summary = "Update a device ")
    @ApiResponses(value = { 
      @ApiResponse(responseCode = "200", description = "Updated the device", 
        content = { @Content(mediaType = "application/json", 
          schema = @Schema(implementation = Device.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid device supplied", 
        content = @Content), 
      @ApiResponse(responseCode = "404", description = "Device not found", 
        content = @Content) })
    @PutMapping
    public ResponseEntity<DeviceResponse> updateDevice(@io.swagger.v3.oas.annotations.parameters.RequestBody(
    	    description = "Device to update", required = true,
    	    content = @Content(mediaType = "application/json",
    	      schema = @Schema(implementation = Device.class),
    	      examples = @ExampleObject(value = "{ \"id\": 1, \"name\": \"Existing Device\", \"brand\": \"Brand Name\", \"state\": \"AVAILABLE\", \"creationTime\": \"28/05/2025 03:36:37\" }"))) @Valid   @RequestBody DeviceRequest deviceRequest){
        Device device = mapper.map(deviceRequest,  Device.class);
        Device deviceSaved = updateDeviceUseCase.updateDevice(device);
    	DeviceResponse deviceResponse = mapper.map(deviceSaved, DeviceResponse.class);

        return new ResponseEntity<>(deviceResponse, HttpStatus.OK);
    }
    
    @Operation(summary = "Delete a device ")
    @ApiResponses(value = { 
      @ApiResponse(responseCode = "200", description = "Deleted the device", 
        content = { @Content(mediaType = "application/json", 
          schema = @Schema(implementation = Device.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
        content = @Content), 
      @ApiResponse(responseCode = "404", description = "Device not found", 
        content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<DeviceResponse> deleteDevice(@Parameter(description = "id of a device to be deleted") @PathVariable("id") Long id) throws NotFoundException{
        deleteDeviceUseCase.deleteDeviceById(id);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }


    
}
