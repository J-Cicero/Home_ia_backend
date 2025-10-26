package Home_ia.home.shared.common.application.dtos.response;

import Home_ia.home.shared.common.domain.enums.TypeRole;

import java.util.UUID;

public record UserResponse(
        UUID trackingId,
        String name,
        String surname,
        String email,
        TypeRole role
) {
}
