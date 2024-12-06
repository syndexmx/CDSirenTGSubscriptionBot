package syndexmx.github.com.tgsiren.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "subscribers")
public class Subscriber implements Serializable {

    @Id
            @Column(name = "subscriber_id")
    Long id;

}
