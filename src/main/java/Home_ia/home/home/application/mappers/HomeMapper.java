package Home_ia.home.home.application.mappers;

import Home_ia.home.home.application.dtos.requests.HomeRequest;
import Home_ia.home.home.application.dtos.responses.HomeResponse;
import Home_ia.home.home.domain.models.Home;
import Home_ia.home.home.domain.models.Room;
import Home_ia.home.home.infrastructure.repositories.RoomRepository;
import Home_ia.home.shared.common.infrastructure.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
@RequiredArgsConstructor
public class HomeMapper {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    private Home toEntity(HomeRequest request){

        if(request == null){
            throw new IllegalArgumentException("request cannot null ");
        }

        Home home = new Home();
        home.setTrackingId(UUID.randomUUID());
        home.setName(request.name());
        home.setLatitude(request.latitude());
        home.setLongitude(request.longitude());
        home.setAdress(request.adress());

        Room room  = roomRepository.findBytrackingId(request.trackingRoom_Id())
                .orElseThrow(() ->  new EntityNotFoundException("never Room entity element find with this trackingId :" + request.trackingRoom_Id()));

        return home;
    }

    private HomeResponse toResposne(Home home){

        if(home == null){
            throw new IllegalArgumentException(" entity cannot null ");
        }

        return new HomeResponse(
                home.getName(),
                home.getAdress(),
                home.getLatitude(),
                home.getLongitude(),
                home.getRoom().getTrackingId(),
                home.getUser().getTrackingId(),
                home.getTrackingId()
        );
    }

    private static Home toEntityFromResponse(HomeResponse response){

        if(response == null){
            new IllegalArgumentException(" esponse cannot null ");
        }

        Home home = new Home();
        home.setName(response.name());

        return home;
    }
}
