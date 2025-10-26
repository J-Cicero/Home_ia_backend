package Home_ia.home.home.application.mappers;

import Home_ia.home.home.application.dtos.requests.DeviceRequest;
import Home_ia.home.home.domain.models.Device;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeviceMapper {

    public Device toEntity(DeviceRequest request){
        if(request == null){
            throw new IllegalArgumentException(" request cannot null");
        }

        Device device = new Device();
        device.setTrackingId(UUID.randomUUID());
        device.setName(request.name());
        device.setEtat(request.etat());
        device.setAdressIp(request.adressIp());

        return device;
    }


}
