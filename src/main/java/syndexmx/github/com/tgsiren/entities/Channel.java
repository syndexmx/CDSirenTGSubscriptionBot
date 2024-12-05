package syndexmx.github.com.tgsiren.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "channels")
public class Channel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "channel_id")
    private Long id;

    @Column(name = "channel_name")
    private String name;

    @Column(name = "channel_url")
    private String url;

}
