package syndexmx.github.com.tgsiren.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subscriptions")
public class Subscription {

    @Id
            @Column(name = "subscription_id")
    Long id;

    @OneToOne
            @Column(name = "subscriber_reference")
    Subscriber subscriber;

    @OneToOne
            @Column(name = "channel_reference")
    Channel channel;

}
