package syndexmx.github.com.tgsiren.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "subscribers")
public class Subscriber implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "subscriber_id")
    Long id;

}
