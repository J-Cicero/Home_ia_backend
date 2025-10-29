package Home_ia.home.application.dtos.requests;

import Home_ia.home.domain.enums.DeviceEtat;

import java.util.UUID;

public record DeviceRequest(

        UUID trackingId,

        String name,

        String adressIp,

        DeviceEtat etat
) {
}
