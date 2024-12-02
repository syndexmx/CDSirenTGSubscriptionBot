package syndexmx.github.com.tgsiren.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "subscribers")
public class Subscriber {

    @Id
            @Column(name = "subscriber_id")
    Long id;

}
