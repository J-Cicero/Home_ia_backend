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
import Home_ia.home.application.dtos.requests.RoomRequest;
import Home_ia.home.application.dtos.responses.RoomResponse;
import Home_ia.home.application.services.RoomService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rooms")
@CrossOrigin("*")
@Tag(name = "Rooms", description = "API for room management")
public class RoomController {
    
    private final RoomService roomService;

    @PostMapping
    @Operation(summary = "Create a new room", description = "Creates a new room")
    @ApiResponse(responseCode = "201", description = "Room created successfully",
            content = @Content(schema = @Schema(implementation = RoomResponse.class)))
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    public ResponseEntity<?> createRoom(@RequestBody RoomRequest request) {
        try {
            RoomResponse response = roomService.createRoom(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "ROOM_CREATION_FAILED", "message", e.getMessage()));
        }
    }

    @GetMapping("/{trackingId}")
    @Operation(summary = "Get room by ID", description = "Retrieves a room by their tracking ID")
    @ApiResponse(responseCode = "200", description = "Room found",
            content = @Content(schema = @Schema(implementation = RoomResponse.class)))
    @ApiResponse(responseCode = "404", description = "Room not found")
    public ResponseEntity<?> getRoomByTrackingId(@PathVariable UUID trackingId) {
        try {
            RoomResponse response = roomService.getRoomByTrackingId(trackingId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "ROOM_NOT_FOUND", "message", e.getMessage()));
        }
    }

    @GetMapping
    @Operation(summary = "Get all rooms", description = "Retrieves a list of all rooms")
    public ResponseEntity<List<RoomResponse>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRoom());
    }

    @PutMapping("/{trackingId}")
    @Operation(summary = "Update room", description = "Updates an existing room's information")
    @ApiResponse(responseCode = "200", description = "Room updated successfully",
            content = @Content(schema = @Schema(implementation = RoomResponse.class)))
    @ApiResponse(responseCode = "404", description = "Room not found")
    public ResponseEntity<?> updateRoom(
            @PathVariable UUID trackingId,
            @RequestBody RoomRequest request) {
        try {
            RoomResponse response = roomService.updateRoom(trackingId, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "ROOM_UPDATE_FAILED", "message", e.getMessage()));
        }
    }

    @DeleteMapping("/{trackingId}")
    @Operation(summary = "Delete room", description = "Deletes a room by their tracking ID")
    @ApiResponse(responseCode = "204", description = "Room deleted successfully")
    @ApiResponse(responseCode = "404", description = "Room not found")
    public ResponseEntity<?> deleteRoom(@PathVariable UUID trackingId) {
        try {
            roomService.deleteRoom(trackingId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "ROOM_DELETE_FAILED", "message", e.getMessage()));
        }
    }
}
