package Home_ia.home.home.infrastructure.repositories;

import Home_ia.home.home.domain.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Long, Room> {

    Optional<Room> findBytrackingId(UUID trackingId);
}
