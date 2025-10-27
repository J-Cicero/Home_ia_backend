package Home_ia.home.home.application.mappers;


import Home_ia.home.home.application.dtos.requests.RoomRequest;
import Home_ia.home.home.application.dtos.responses.DeviceResponse;
import Home_ia.home.home.application.dtos.responses.RoomResponse;
import Home_ia.home.home.domain.models.Device;
import Home_ia.home.home.domain.models.Room;
import Home_ia.home.home.infrastructure.repositories.DeviceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RoomMapper {

    private DeviceRepository deviceRepository;

    public Room toEntity(RoomRequest request) {

        if (request == null) {
            throw new IllegalArgumentException("request cannot null");
        }
        Room room = new Room();
        room.setTrackingId(UUID.randomUUID());
        room.setName(request.name());

        Device device = deviceRepository.findByTrackingId(request.trackingDevice_Id())
                .orElseThrow(() -> new EntityNotFoundException(" we don't find an entity Room with this trackingid :" + request.trackingDevice_Id()));

        room.setDevice(device);

        return room;

    }

    public RoomResponse toResponse(Room room){
        if(room == null){
            throw new IllegalArgumentException(" entity cannot null");
        }

        return new RoomResponse(
                room.getTrackingId(),
                room.getName(),
                room.getDevice().getTrackingId()
        );
    }

    public Room toEntityFromResponse(RoomResponse response){
        if(response == null){
            throw new IllegalArgumentException("response cannot null");
        }

        Room room = new Room();
        room.setName(response.name());

        return room;
    }
}
