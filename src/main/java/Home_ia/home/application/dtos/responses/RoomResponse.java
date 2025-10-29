package Home_ia.home.application.dtos.responses;


import java.util.UUID;

public record RoomResponse(
        UUID trackingId,

        String name,

        UUID device
) {
}
