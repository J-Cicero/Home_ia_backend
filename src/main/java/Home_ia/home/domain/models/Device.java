package Home_ia.home.domain.models;

import Home_ia.home.domain.enums.DeviceEtat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "DEVICE")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true , nullable = false )
    private UUID trackingId;

    @Column(name="adressIp" , nullable = false )
    private String AdressIp;

    @Enumerated(EnumType.STRING)
    private DeviceEtat etat;



}
