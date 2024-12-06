package syndexmx.github.com.tgsiren.entities;

import jakarta.persistence.*;
import jakarta.validation.Constraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "feed_messages")
public class FeedMessage implements Serializable {

    @Id
            @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "footer")
    String footer;

    @Column(name = "text")
    String text;

    @Column(name = "owner")
    String owner;

}
