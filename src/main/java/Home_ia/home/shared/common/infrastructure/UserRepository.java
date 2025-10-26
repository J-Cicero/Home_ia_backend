package Home_ia.home.shared.common.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import Home_ia.home.shared.common.domain.models.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByTrackingId(UUID trackingId);
}
