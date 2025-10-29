package Home_ia.home.application.services.serviceImpl;

import Home_ia.home.application.dtos.requests.RoomRequest;
import Home_ia.home.application.dtos.responses.RoomResponse;
import Home_ia.home.application.mappers.RoomMapper;
import Home_ia.home.application.services.RoomService;
import Home_ia.home.domain.models.Room;
import Home_ia.home.infrastructure.repositories.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private RoomMapper roomMapper;
    private RoomRepository roomRepository;


    @Override
    public RoomResponse createRoom(RoomRequest request) {
        Room room = roomMapper.toEntity(request);
        Room saved = roomRepository.save(room);

        return roomMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public RoomResponse getRoomByTrackingId(UUID trackingId) {
        Room room = roomRepository.findBytrackingId(trackingId)
                .orElseThrow(() -> new EntityNotFoundException("we cannot find room with this trackingId : " + trackingId));

       return roomMapper.toResponse(room);
    }

    @Override
    public List<RoomResponse> getAllRoom() {
        return roomRepository.findAll()
                .stream()
                .map(roomMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RoomResponse updateRoom(UUID trackingId, RoomRequest request) {
        Room existingRoom = roomRepository.findBytrackingId(trackingId)
                .orElseThrow(()-> new EntityNotFoundException("we cannot find room with this trackingId : "));

        existingRoom.setName(request.name());

        Room updated = roomRepository.save(existingRoom);

        return roomMapper.toResponse(updated);
    }

    @Override
    public void deleteRoom(UUID trackingId) {
        Room room = roomRepository.findBytrackingId(trackingId)
                .orElseThrow(()-> new EntityNotFoundException("we cannot find room with this trackingId : "));

        roomRepository.delete(room);

    }
}
