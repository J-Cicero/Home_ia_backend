package Home_ia.home.home.infrastructure.repositories;

import Home_ia.home.home.domain.models.Home;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface HomeRepository extends JpaRepository<Long, Home> {

    Optional<Home> findByTrackingId(UUID trackingId);

}
