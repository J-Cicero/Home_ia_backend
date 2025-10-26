package Home_ia.home.shared.common.domain.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import Home_ia.home.shared.common.domain.enums.TypeRole;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true , nullable = false )
    private UUID trackingId;

    @Column( nullable = false , length = 40 )
    private String name;

    @Column( nullable = false , length = 40 )
    private String surname;

    @Column( nullable = false , length = 40 )
    private String password;

    @Column( nullable = false , length = 40 )
    private String email;

    @Column( nullable = false , length = 40 )
    private TypeRole role;
}
