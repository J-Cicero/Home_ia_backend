package Home_ia.home.application.services.serviceImpl;

import Home_ia.home.application.dtos.requests.DeviceRequest;
import Home_ia.home.application.dtos.responses.DeviceResponse;
import Home_ia.home.application.mappers.DeviceMapper;
import Home_ia.home.application.services.DeviceService;
import Home_ia.home.domain.models.Device;
import Home_ia.home.infrastructure.repositories.DeviceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private DeviceMapper deviceMapper;
    private DeviceRepository deviceRepository;

    @Override
    public DeviceResponse createDevice(DeviceRequest request) {

        Device device = deviceMapper.toEntity(request);
        Device saved =  deviceRepository.save(device);

        return deviceMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public DeviceResponse getDeviceByTrackingid(UUID trackingId) {

        Device device = deviceRepository.findByTrackingId(trackingId)
                .orElseThrow(() -> new EntityNotFoundException(" we don't find an entity where trackingId =  " + trackingId ));

       return deviceMapper.toResponse(device);
    }

    @Override
    @Transactional
    public List<DeviceResponse> getAllDevice() {

        return  deviceRepository.findAll()
                .stream()
                .map(deviceMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DeviceResponse updateDevice(UUID trackingId, DeviceRequest request) {
        Device existingDevice = deviceRepository.findByTrackingId(trackingId)
                .orElseThrow(() -> new EntityNotFoundException(" we don't find an entity where trackingId =  " + trackingId ));

        existingDevice.setAdressIp(request.adressIp());
        existingDevice.setName(request.name());
        existingDevice.setEtat(request.etat());

        Device updated = deviceRepository.save(existingDevice);
        return deviceMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void deleteDevice(UUID trackingId) {
        Device device = deviceRepository.findByTrackingId(trackingId)
                .orElseThrow(() -> new EntityNotFoundException(" we don't find an entity where trackingId =  " + trackingId ));

        deviceRepository.delete(device);
    }
}
