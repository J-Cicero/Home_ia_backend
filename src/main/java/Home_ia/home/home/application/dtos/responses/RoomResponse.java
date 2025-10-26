package Home_ia.home.home.application.dtos.responses;


import java.util.UUID;

public record RoomResponse(
        UUID trackingId,

        String name,

        UUID device
) {
}
