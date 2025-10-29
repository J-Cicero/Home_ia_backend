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
import Home_ia.home.application.dtos.requests.HomeRequest;
import Home_ia.home.application.dtos.responses.HomeResponse;
import Home_ia.home.application.services.HomeService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/homes")
@CrossOrigin("*")
@Tag(name = "Homes", description = "API for home management")
public class HomeController {
    
    private final HomeService homeService;

    @PostMapping
    @Operation(summary = "Create a new home", description = "Creates a new home")
    @ApiResponse(responseCode = "201", description = "Home created successfully",
            content = @Content(schema = @Schema(implementation = HomeResponse.class)))
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    public ResponseEntity<?> createHome(@RequestBody HomeRequest request) {
        try {
            HomeResponse response = homeService.createHome(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "HOME_CREATION_FAILED", "message", e.getMessage()));
        }
    }

    @GetMapping("/{trackingId}")
    @Operation(summary = "Get home by ID", description = "Retrieves a home by their tracking ID")
    @ApiResponse(responseCode = "200", description = "Home found",
            content = @Content(schema = @Schema(implementation = HomeResponse.class)))
    @ApiResponse(responseCode = "404", description = "Home not found")
    public ResponseEntity<?> getHomeByTrackingId(@PathVariable UUID trackingId) {
        try {
            HomeResponse response = homeService.getHomeByTrackingId(trackingId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "HOME_NOT_FOUND", "message", e.getMessage()));
        }
    }

    @GetMapping
    @Operation(summary = "Get all homes", description = "Retrieves a list of all homes")
    public ResponseEntity<List<HomeResponse>> getAllHomes() {
        return ResponseEntity.ok(homeService.getAllHome());
    }

    @PutMapping("/{trackingId}")
    @Operation(summary = "Update home", description = "Updates an existing home's information")
    @ApiResponse(responseCode = "200", description = "Home updated successfully",
            content = @Content(schema = @Schema(implementation = HomeResponse.class)))
    @ApiResponse(responseCode = "404", description = "Home not found")
    public ResponseEntity<?> updateHome(
            @PathVariable UUID trackingId,
            @RequestBody HomeRequest request) {
        try {
            HomeResponse response = homeService.updateHome(trackingId, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "HOME_UPDATE_FAILED", "message", e.getMessage()));
        }
    }

    @DeleteMapping("/{trackingId}")
    @Operation(summary = "Delete home", description = "Deletes a home by their tracking ID")
    @ApiResponse(responseCode = "204", description = "Home deleted successfully")
    @ApiResponse(responseCode = "404", description = "Home not found")
    public ResponseEntity<?> deleteHome(@PathVariable UUID trackingId) {
        try {
            homeService.deleteHome(trackingId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "HOME_DELETE_FAILED", "message", e.getMessage()));
        }
    }
}
