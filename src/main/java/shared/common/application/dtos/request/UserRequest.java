package shared.common.application.dtos.request;

import shared.common.domain.enums.TypeRole;

public record UserRequest (
        String name,
        String surname,
        String email,
        String password,
        TypeRole role
){ }
