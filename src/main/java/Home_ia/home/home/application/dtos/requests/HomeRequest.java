package Home_ia.home.home.application.dtos.requests;

import java.util.UUID;

public record HomeRequest (

     String name,

     String adress,

     double latitude,

     double longitude,

     UUID trackingRoom_Id,

     UUID trackingUser_Id

){ }
