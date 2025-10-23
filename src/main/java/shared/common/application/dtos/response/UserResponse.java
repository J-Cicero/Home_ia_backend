package shared.common.application.dtos.response;

import shared.common.domain.enums.TypeRole;

import java.util.UUID;

public record UserResponse(
        UUID trackingId,
        String name,
        String surname,
        String email,
        TypeRole role
) {
}
