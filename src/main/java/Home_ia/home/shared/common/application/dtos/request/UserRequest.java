package Home_ia.home.shared.common.application.dtos.request;

import Home_ia.home.shared.common.domain.enums.TypeRole;

public record UserRequest (
        String name,
        String surname,
        String email,
        String password,
        TypeRole role
){ }
