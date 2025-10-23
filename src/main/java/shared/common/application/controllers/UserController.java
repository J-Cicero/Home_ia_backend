package shared.common.application.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shared.common.application.dtos.request.UserRequest;
import shared.common.application.dtos.response.UserResponse;
import shared.common.application.services.UserService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
@Tag(name = "Users", description = "API for user management")
public class UserController {
    
    private final UserService userService;

    @PostMapping
    @Operation(summary = "Create a new user", description = "Creates a new user account")
    @ApiResponse(responseCode = "201", description = "User created successfully",
            content = @Content(schema = @Schema(implementation = UserResponse.class)))
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    public ResponseEntity<?> createUser(@RequestBody UserRequest request) {
        try {
            UserResponse response = userService.createUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "USER_CREATION_FAILED", "message", e.getMessage()));
        }
    }

    @GetMapping("/{trackingId}")
    @Operation(summary = "Get user by ID", description = "Retrieves a user by their ID")
    @ApiResponse(responseCode = "200", description = "User found",
            content = @Content(schema = @Schema(implementation = UserResponse.class)))
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<?> getUserByTrackingId(@PathVariable UUID trackingId) {
        try {
            UserResponse response = userService.getUserByTrackignId(trackingId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "USER_NOT_FOUND", "message", e.getMessage()));
        }
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieves a paginated list of all users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PutMapping("/{trackingId}")
    @Operation(summary = "Update user", description = "Updates an existing user's information")
    @ApiResponse(responseCode = "200", description = "User updated successfully",
            content = @Content(schema = @Schema(implementation = UserResponse.class)))
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<?> updateUser(
            @PathVariable UUID trackingId,
            @RequestBody UserRequest request) {
        try {
            UserResponse response = userService.update(trackingId, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "USER_UPDATE_FAILED", "message", e.getMessage()));
        }
    }

    @DeleteMapping("/{trackingId}")
    @Operation(summary = "Delete user", description = "Deletes a user by their ID")
    @ApiResponse(responseCode = "204", description = "User deleted successfully")
    @ApiResponse(responseCode = "404", description = "User not found")
    public ResponseEntity<?> deleteUser(@PathVariable UUID trackingId) {
        try {
            userService.deleteUser(trackingId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "USER_DELETE_FAILED", "message", e.getMessage()));
        }
    }
}
