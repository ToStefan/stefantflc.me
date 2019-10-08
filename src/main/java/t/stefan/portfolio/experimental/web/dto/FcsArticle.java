package t.stefan.portfolio.experimental.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FcsArticle {
	
	private Long id;
	private String fullContent;
	private String date;
	private String title;
	private String picture;
	private String type;
	private String link;
	private String description;

}
