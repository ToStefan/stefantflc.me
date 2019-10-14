package t.stefan.portfolio.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FcsArticleDTO {
	
    private Long id;
    private String title;
    private String date;
    private String description;
    private String type;
    private String link;
    private String picture;
    private String content;

}
