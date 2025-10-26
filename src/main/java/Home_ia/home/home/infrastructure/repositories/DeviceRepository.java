package Home_ia.home.home.infrastructure.repositories;

import Home_ia.home.home.domain.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeviceRepository  extends JpaRepository<Long,Device> {

    Optional<Device> findByTrackingId(UUID trackingId);
}
