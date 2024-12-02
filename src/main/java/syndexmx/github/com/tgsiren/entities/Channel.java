package syndexmx.github.com.tgsiren.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "channels")
public class Channel {

    @Id
            @Column(name = "channel_id")
    Long id;

    @Column(name = "channel_name")
    String name;

    @Column(name = "channel_url")
    String url;

    @OneToMany
    List<Filter> filters;
}
