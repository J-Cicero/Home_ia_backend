package Home_ia.home.home.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import Home_ia.home.shared.common.domain.models.User;

import java.util.UUID;

@Entity
@Table(name="HOME")
@Getter
@Setter
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String adress;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @ManyToOne(fetch = FetchType.EAGER )
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private UUID trackingId;


}
