package t.stefan.portfolio.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "client_details")
public class ClientDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "user_agent")
    private String userAgent;

    private String location;

    private String region;

    private String country;

    private String city;

    private String ip;

    private String user;

    private Integer count;

    private String path;
}