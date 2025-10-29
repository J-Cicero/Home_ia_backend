package Home_ia.home.application.dtos.responses;

import Home_ia.home.domain.enums.DeviceEtat;

import java.util.UUID;

public record DeviceResponse (

        UUID trackingId,

        String name,

         String adressIp,

         DeviceEtat etat
){
}
