package t.stefan.portfolio.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessageDTO {

    private String message;
    private String from;
    private OffsetDateTime time;
}

