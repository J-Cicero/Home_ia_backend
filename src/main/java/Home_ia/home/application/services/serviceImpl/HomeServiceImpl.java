package Home_ia.home.application.services.serviceImpl;

import Home_ia.home.application.dtos.requests.HomeRequest;
import Home_ia.home.application.dtos.responses.HomeResponse;
import Home_ia.home.application.mappers.HomeMapper;
import Home_ia.home.application.services.HomeService;
import Home_ia.home.domain.models.Home;
import Home_ia.home.infrastructure.repositories.HomeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class HomeServiceImpl implements HomeService {

    private HomeRepository homeRepository;
    private HomeMapper homeMapper;

    @Override
    public HomeResponse createHome(HomeRequest request) {

        Home home = homeMapper.toEntity(request);
        Home saved = homeRepository.save(home);

        return homeMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public HomeResponse getHomeByTrackingId(UUID trackingId) {
        Home home = homeRepository.findByTrackingId(trackingId)
                .orElseThrow(() -> new EntityNotFoundException(" we don't find an entity where trackingId =  " + trackingId ));

        return homeMapper.toResponse(home);
    }

    @Override
    @Transactional
    public List<HomeResponse> getAllHome() {
        return homeRepository.findAll()
                .stream()
                .map(homeMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public HomeResponse updateHome(UUID trackingId, HomeRequest request) {

        Home existingHome = homeRepository.findByTrackingId(trackingId)
                .orElseThrow(() -> new EntityNotFoundException(" we don't find an entity where trackingId =  " + trackingId ));

        existingHome.setName(request.name());
        existingHome.setAdress(request.adress());
        existingHome.setLatitude(request.latitude());
        existingHome.setLongitude(request.longitude());

        Home updatedHome = homeRepository.save(existingHome);
        return homeMapper.toResponse(updatedHome);
    }

    @Override
    public void deleteHome(UUID trackingId) {
        Home home = homeRepository.findByTrackingId(trackingId)
                .orElseThrow(() -> new EntityNotFoundException(" we don't find an entity where trackingId =  " + trackingId ));

        homeRepository.delete(home);
    }
}
