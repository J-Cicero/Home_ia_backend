package Home_ia.home.shared.common.application.mappers;

import org.springframework.stereotype.Component;
import Home_ia.home.shared.common.application.dtos.request.UserRequest;
import Home_ia.home.shared.common.application.dtos.response.UserResponse;
import Home_ia.home.shared.common.domain.models.User;

import java.util.UUID;

@Component
public class UserMapper {

  public User toEntity(UserRequest request){
      if(request == null){
            throw new IllegalArgumentException("UserRequest cannot be null");
      }
      User user = new User();
      user.setTrackingId(UUID.randomUUID());
      user.setName(request.name());
      user.setSurname(request.surname());
      user.setEmail(request.email());
      user.setRole(request.role());

      return user;
  }

  public UserResponse toResponse(User user){
        if(user == null){
            throw new IllegalArgumentException(" the entity user cannot null");
        }
        return new UserResponse(
                user.getTrackingId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getRole()
        );
  }

  public User toEntityFromResponse(UserResponse response){
      if(response == null){
          throw new IllegalArgumentException("UserResponse cannot be null");
      }
      User user = new User();
      user.setTrackingId(response.trackingId());
      user.setName(response.name());
      user.setSurname(response.surname());

      return user;
  }
}
