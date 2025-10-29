package Home_ia.home.application.dtos.requests;

import java.util.UUID;

public record RoomRequest(

        String name,

        UUID trackingDevice_Id
) {
}
