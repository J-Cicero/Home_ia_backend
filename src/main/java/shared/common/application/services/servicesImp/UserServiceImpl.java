package shared.common.application.services.servicesImp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shared.common.application.dtos.request.UserRequest;
import shared.common.application.dtos.response.UserResponse;
import shared.common.application.mappers.UserMapper;
import shared.common.application.services.UserService;
import shared.common.domain.models.User;
import shared.common.infrastructure.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse createUser(UserRequest request) {
        User user = userMapper.toEntity(request);
        userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse getUserByTrackignId(UUID trackingId) {
        User user = userRepository.findByTrackingId(trackingId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse update(UUID trackingId, UserRequest request) {

        User user = userRepository.findByTrackingId(trackingId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setName(request.name());
        user.setSurname(request.surname());

        userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public void deleteUser(UUID trackingId) {
        User user = userRepository.findByTrackingId(trackingId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public List<UserResponse> getAllUser() {
           return userRepository.findAll()
            .stream()
            .map(userMapper::toResponse)
            .collect(Collectors.toList());
    }
}
