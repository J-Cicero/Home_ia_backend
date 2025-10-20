package home.domain.models;

import home.domain.enums.DeviceEtat;
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
    private Long Id;

    @Column(unique = true , nullable = false )
    private UUID trackingId;

    @Column(name="adressIp" , nullable = false )
    private String AdressIp;

    @Enumerated(EnumType.STRING)
    private DeviceEtat etat;



}
