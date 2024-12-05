package syndexmx.github.com.tgsiren.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subscriptions")
public class Subscription implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    Long id;

    @Column(name = "subscriber_ref")
    Subscriber subscriber;

    @Column(name = "subscription_ref")
    Subscription subscription;


}
