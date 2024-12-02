package syndexmx.github.com.tgsiren.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsFeedMessage {

    @Id
            @Column(name = "url")
    String url;

    @Column(name = "owner")
    String owner;

    @Column(name = "text")
    String text;

    @Column(name = "datetime")
    String dateTime;

    @Column(name = "time")
    String time;

}
