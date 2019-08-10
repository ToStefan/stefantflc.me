package t.stefan.portfolio.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ClientDetailsDTO {

    private Long id;
    private LocalDateTime dateTime;
    private String userAgent;
    private String location;
    private String region;
    private String country;
    private String city;
    private String ip;
    private Integer count;
    private String user;
    private String path;

}