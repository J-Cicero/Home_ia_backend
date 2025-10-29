package Home_ia.home.application.services;

import Home_ia.home.application.dtos.requests.HomeRequest;
import Home_ia.home.application.dtos.responses.HomeResponse;

import java.util.List;
import java.util.UUID;

public interface HomeService {

    HomeResponse createHome(HomeRequest request );

    HomeResponse getHomeByTrackingId(UUID trackingId);

    List<HomeResponse> getAllHome();

    HomeResponse updateHome(UUID trackingId , HomeRequest request);

    void deleteHome(UUID trackingId);
}
