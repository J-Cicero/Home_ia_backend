package Home_ia.home.infrastructure.repositories;

import Home_ia.home.domain.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeviceRepository  extends JpaRepository<Device,Long> {

    Optional<Device> findByTrackingId(UUID trackingId);
}
