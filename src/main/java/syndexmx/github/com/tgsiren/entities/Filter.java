package syndexmx.github.com.tgsiren.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table (name = "filters")
public class Filter {

    @Id
            @Column(name = "filter_id")
    Long id;

    @ManyToOne
            @Column(name = "host_id")
    Channel hostId;

    @Column(name = "filter_string")
    String filterString;

}
