package Home_ia.home.application.services;

import Home_ia.home.application.dtos.requests.RoomRequest;
import Home_ia.home.application.dtos.responses.RoomResponse;

import java.util.List;
import java.util.UUID;

public interface RoomService {

    RoomResponse createRoom( RoomRequest request);

    RoomResponse getRoomByTrackingId(UUID trackingId);

    List<RoomResponse> getAllRoom();

    RoomResponse updateRoom(UUID trackingId , RoomRequest request );

    void deleteRoom(UUID trackingId);
}
