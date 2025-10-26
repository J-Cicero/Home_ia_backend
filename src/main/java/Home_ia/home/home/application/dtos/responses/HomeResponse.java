package Home_ia.home.home.application.dtos.responses;

import java.util.UUID;

public record HomeResponse(

        String name,

        String adress,

        double latitude,

        double longitude,

        UUID trackingRoom_Id,

        UUID trackingUser_Id,

        UUID trackingId
) {
}
