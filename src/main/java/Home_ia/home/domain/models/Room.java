package Home_ia.home.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "ROOM")
@Getter
@Setter
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  Id ;

    @Column(name = "trackingId" ,  nullable = false)
    private UUID trackingId;

    @Column(name = "name" , nullable = false , length = 30)
    private String name;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "device_id")
   private Device device;


}
