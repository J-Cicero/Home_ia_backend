package Home_ia.home.application.services;

import Home_ia.home.application.dtos.requests.DeviceRequest;
import Home_ia.home.application.dtos.responses.DeviceResponse;

import java.util.List;
import java.util.UUID;

public interface DeviceService {

    DeviceResponse createDevice(DeviceRequest request);

    DeviceResponse getDeviceByTrackingid(UUID trackingId);

    List<DeviceResponse> getAllDevice();

    DeviceResponse updateDevice(UUID trackingId, DeviceRequest request);

    void deleteDevice(UUID trackingId);

}
