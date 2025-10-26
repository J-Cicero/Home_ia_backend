package Home_ia.home.shared.common.application.services;

import Home_ia.home.shared.common.application.dtos.request.UserRequest;
import Home_ia.home.shared.common.application.dtos.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponse createUser(UserRequest request) ;

    UserResponse getUserByTrackignId(UUID trackingId);

    UserResponse update(UUID trackingId , UserRequest request);

    void deleteUser(UUID trackingId);

    List<UserResponse> getAllUser();
}
