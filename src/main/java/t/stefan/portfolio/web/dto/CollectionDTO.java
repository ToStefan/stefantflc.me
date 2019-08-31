package t.stefan.portfolio.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CollectionDTO {

    private Long id;
    private String name;
    private String description;
    private Long userId;
    private String user;
    private List<ImageDTO> images;
}
