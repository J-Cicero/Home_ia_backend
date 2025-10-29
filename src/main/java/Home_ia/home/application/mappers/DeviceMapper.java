package Home_ia.home.application.mappers;

import Home_ia.home.application.dtos.requests.DeviceRequest;
import Home_ia.home.application.dtos.responses.DeviceResponse;
import Home_ia.home.domain.models.Device;
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

    public DeviceResponse toResponse(Device device){

        if(device == null){
            throw new IllegalArgumentException("entity cannot null");
        }

        return new DeviceResponse(
                device.getTrackingId(),
                device.getName(),
                device.getAdressIp(),
                device.getEtat()
        );

    }

    public Device toEntityFromResponse(DeviceResponse response){
        if(response == null){
            throw new IllegalArgumentException("response cannot null");
        }

        Device device = new Device();

        device.setName(response.name());
        device.setEtat(response.etat());
        device.setAdressIp(response.adressIp());

        return device;
    }
}
