package Home_ia.home.application.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Home_ia.home.application.dtos.requests.DeviceRequest;
import Home_ia.home.application.dtos.responses.DeviceResponse;
import Home_ia.home.application.services.DeviceService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/devices")
@CrossOrigin("*")
@Tag(name = "Devices", description = "API for device management")
public class DeviceController {
    
    private final DeviceService deviceService;

    @PostMapping
    @Operation(summary = "Create a new device", description = "Creates a new device")
    @ApiResponse(responseCode = "201", description = "Device created successfully",
            content = @Content(schema = @Schema(implementation = DeviceResponse.class)))
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    public ResponseEntity<?> createDevice(@RequestBody DeviceRequest request) {
        try {
            DeviceResponse response = deviceService.createDevice(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "DEVICE_CREATION_FAILED", "message", e.getMessage()));
        }
    }

    @GetMapping("/{trackingId}")
    @Operation(summary = "Get device by ID", description = "Retrieves a device by their tracking ID")
    @ApiResponse(responseCode = "200", description = "Device found",
            content = @Content(schema = @Schema(implementation = DeviceResponse.class)))
    @ApiResponse(responseCode = "404", description = "Device not found")
    public ResponseEntity<?> getDeviceByTrackingId(@PathVariable UUID trackingId) {
        try {
            DeviceResponse response = deviceService.getDeviceByTrackingid(trackingId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "DEVICE_NOT_FOUND", "message", e.getMessage()));
        }
    }

    @GetMapping
    @Operation(summary = "Get all devices", description = "Retrieves a list of all devices")
    public ResponseEntity<List<DeviceResponse>> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevice());
    }

    @PutMapping("/{trackingId}")
    @Operation(summary = "Update device", description = "Updates an existing device's information")
    @ApiResponse(responseCode = "200", description = "Device updated successfully",
            content = @Content(schema = @Schema(implementation = DeviceResponse.class)))
    @ApiResponse(responseCode = "404", description = "Device not found")
    public ResponseEntity<?> updateDevice(
            @PathVariable UUID trackingId,
            @RequestBody DeviceRequest request) {
        try {
            DeviceResponse response = deviceService.updateDevice(trackingId, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "DEVICE_UPDATE_FAILED", "message", e.getMessage()));
        }
    }

    @DeleteMapping("/{trackingId}")
    @Operation(summary = "Delete device", description = "Deletes a device by their tracking ID")
    @ApiResponse(responseCode = "204", description = "Device deleted successfully")
    @ApiResponse(responseCode = "404", description = "Device not found")
    public ResponseEntity<?> deleteDevice(@PathVariable UUID trackingId) {
        try {
            deviceService.deleteDevice(trackingId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "DEVICE_DELETE_FAILED", "message", e.getMessage()));
        }
    }
}
