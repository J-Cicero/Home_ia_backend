package Home_ia.home.home.application.dtos.requests;

import java.util.UUID;

public record RoomRequest(

        String name,

        UUID device
) {
}
