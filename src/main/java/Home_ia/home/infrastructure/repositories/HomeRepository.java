package Home_ia.home.infrastructure.repositories;

import Home_ia.home.domain.models.Home;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface HomeRepository extends JpaRepository<Home,Long> {

    Optional<Home> findByTrackingId(UUID trackingId);

}
