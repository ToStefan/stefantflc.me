package t.stefan.portfolio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "fcs_news")
public class FcsArticle {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String title;
    
    @NotNull
    private String date;
    
    @NotNull
    private String description;
    
    @NotNull
    private String type;
    
    @NotNull
    private String link;
    
    @NotNull
    private String picture;
    
    @Lob
    @Column(name="content", columnDefinition="LONGBLOB")
    private String content;

}
